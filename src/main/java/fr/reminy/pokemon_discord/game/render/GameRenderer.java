package fr.reminy.pokemon_discord.game.render;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.PokemonGame;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.maps.Map;
import org.mapeditor.core.TileLayer;
import org.mapeditor.view.OrthogonalRenderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
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

        Map map = game.getCurrentMap();
        int tileSize = map.getTileSize();

        BufferedImage image = new BufferedImage(camera.getWidth() * tileSize * Settings.SCALE_FACTOR, camera.getHeight() * tileSize * Settings.SCALE_FACTOR, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();


        graphics2D.setClip(0, 0, camera.getAbsWidth() * Settings.SCALE_FACTOR, camera.getAbsHeight() * Settings.SCALE_FACTOR);
        graphics2D.translate(-camera.getAbsX() + camera.getAbsWidth() / 2 * Settings.SCALE_FACTOR, -camera.getAbsY() + camera.getAbsHeight() / 2 * Settings.SCALE_FACTOR);

        AffineTransform at = new AffineTransform();
        at.translate(camera.getAbsWidth() / 2d * Settings.SCALE_FACTOR, camera.getAbsHeight() / 2d * Settings.SCALE_FACTOR);
        at.scale(Settings.SCALE_FACTOR, Settings.SCALE_FACTOR);
        at.translate(-camera.getAbsX(), -camera.getAbsY());

        graphics2D.setTransform(at);

        map.draw(graphics2D);

        return image;
    }
}
