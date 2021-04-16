package fr.reminy.pokemon_discord.game.http;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class GameHttpServer {
    private BufferedImage image;

    public GameHttpServer() throws IOException {
        image = ImageIO.read(new File("output.png"));
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        HttpContext context = server.createContext("/");
        server.setExecutor(Executors.newFixedThreadPool(10));
        context.setHandler(exchange -> {
            byte[] data = "ok".getBytes();



            OutputStream rep = exchange.getResponseBody();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            data = bos.toByteArray();

            exchange.sendResponseHeaders(200, data.length);

            rep.write(data);
            rep.close();
        });
        server.start();
    }
}
