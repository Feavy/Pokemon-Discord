package fr.reminy.pokemon_discord.game.http;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

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
    public final static GameHttpServer INSTANCE = new GameHttpServer();
    private final Map<Long, BufferedImage> playerImages = new HashMap<>();

    private String serverIP;

    private GameHttpServer() {
        try {
            URL checkip = new URL("http://checkip.amazonaws.com");
            try(BufferedReader in = new BufferedReader(new InputStreamReader(
                    checkip.openStream()))) {
                serverIP = in.readLine();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
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
        //return "http://"+serverIP+"/?player=" + userId + "&cv=" + UUID.randomUUID();
        return "http://localhost/?player=" + userId + "&cv=" + UUID.randomUUID();
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
        }catch (Exception ignore) {
        }
        return result;
    }
}
