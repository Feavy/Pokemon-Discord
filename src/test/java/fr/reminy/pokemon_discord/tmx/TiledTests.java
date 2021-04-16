package fr.reminy.pokemon_discord.tmx;


import org.junit.jupiter.api.Test;
import org.mapeditor.core.Map;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.TileLayer;
import org.mapeditor.view.OrthogonalRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TiledTests {

    @Test
    public void test1() throws Exception {
        ClasspathTMXMapReader reader = new ClasspathTMXMapReader();
        Map map = reader.readMap("/maps/map1.tmx");
        MapLayer layer0 = map.getLayer(0);
        System.out.println(layer0);
        BufferedImage image = new BufferedImage(map.getWidth()*map.getTileWidth(), map.getHeight()*map.getTileHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setClip(0, 0, image.getWidth(), image.getHeight());
        OrthogonalRenderer orthogonalRenderer = new OrthogonalRenderer(map);
        orthogonalRenderer.paintTileLayer(graphics, (TileLayer) map.getLayer(0));
        orthogonalRenderer.paintTileLayer(graphics, (TileLayer) map.getLayer(1));
        ImageIO.write(image, "png", new File("output.png"));
    }

}
