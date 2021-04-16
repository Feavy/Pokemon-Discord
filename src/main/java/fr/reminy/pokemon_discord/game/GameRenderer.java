package fr.reminy.pokemon_discord.game;

import org.mapeditor.core.Map;
import org.mapeditor.core.TileLayer;
import org.mapeditor.view.OrthogonalRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameRenderer {
    private final PokemonGame game;
    private final Camera camera;

    public GameRenderer(PokemonGame game, Camera camera) {
        this.game = game;
        this.camera = camera;
    }

    public BufferedImage render() {
        camera.update();

        Map map = game.getCurrentMap().getMap();
        OrthogonalRenderer orthogonalRenderer = new OrthogonalRenderer(map);

        int tileSize = map.getTileWidth();
        BufferedImage image = new BufferedImage(camera.getWidth() * tileSize, camera.getHeight()*tileSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();


        graphics2D.setClip(0, 0, camera.getAbsWidth(), camera.getAbsHeight());
        graphics2D.translate(-camera.getAbsX()+camera.getAbsWidth()/2, -camera.getAbsY()+camera.getAbsHeight()/2);

        // Draw map layer 0 & 1
        orthogonalRenderer.paintTileLayer(graphics2D, (TileLayer) map.getLayer(0));
        orthogonalRenderer.paintTileLayer(graphics2D, (TileLayer) map.getLayer(1));

        // draw characters
        game.getPlayer().draw(graphics2D);

        // Draw map layer 2
        return image;
    }
}
