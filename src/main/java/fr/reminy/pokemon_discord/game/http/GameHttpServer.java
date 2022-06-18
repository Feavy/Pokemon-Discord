package fr.reminy.pokemon_discord.game.http;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import fr.reminy.pokemon_discord.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;

public class GameHttpServer {

    private static final int PORT = 8081;

    public final static GameHttpServer INSTANCE = new GameHttpServer();
    private final Map<Long, BufferedImage> playerImages = new HashMap<>();

    private String address = "Could not retrieve server public IP address.";

    private GameHttpServer() {
        if (Settings.IS_PRODUCTION) {
            try {
                URL checkip = new URL("http://checkip.amazonaws.com");
                try (BufferedReader in = new BufferedReader(new InputStreamReader(checkip.openStream()))) {
                    String serverIP = in.readLine();
                    this.address = "http://" + serverIP + ":" + PORT;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            NgrokClient ngrokClient = new NgrokClient.Builder().build();
            CreateTunnel createTunnel = new CreateTunnel.Builder().withAddr(PORT).build();

            // Open a HTTP tunnel on port 8080
            // <Tunnel: "http://<public_sub>.ngrok.io" -> "http://localhost:8080">
            Tunnel httpTunnel = ngrokClient.connect(createTunnel);

            address = httpTunnel.getPublicUrl();
        }
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        HttpContext context = server.createContext("/");
        server.setExecutor(Executors.newFixedThreadPool(10));
        context.setHandler(this::handleRequest);
        server.start();
    }

    private void handleRequest(HttpExchange exchange) throws IOException {
        Map<String, String> getParameters = queryToMap(exchange.getRequestURI().getQuery());

        if (!getParameters.containsKey("player")) {
            exchange.sendResponseHeaders(400, 0);
            exchange.getResponseBody().close();
            return;
        }

        long playerId;
        try {
            playerId = Long.parseLong(getParameters.get("player"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(400, 0);
            exchange.getResponseBody().close();
            return;
        }

        BufferedImage image = playerImages.get(playerId);

        if (image == null) {
            exchange.sendResponseHeaders(404, 0);
            exchange.getResponseBody().close();
            return;
        }

        OutputStream rep = exchange.getResponseBody();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        byte[] data = bos.toByteArray();

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "image/png");
        exchange.sendResponseHeaders(200, data.length);

        rep.write(data);
        rep.close();
    }

    public String setPlayerImage(long userId, BufferedImage image) {
        playerImages.put(userId, image);
        return address + "/?player=" + userId + "&cv=" + UUID.randomUUID();
    }

    public Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
        } catch (Exception ignore) {
        }
        return result;
    }
}
