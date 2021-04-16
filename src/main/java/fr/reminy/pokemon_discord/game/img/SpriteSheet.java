package fr.reminy.pokemon_discord.game.img;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private final String name;
    private final BufferedImage image;

    private final int spriteWidth;
    private final int spriteHeight;

    public SpriteSheet(String name, String path, int spriteWidth, int spriteHeight) throws IOException {
        this.name = name;
        this.image = ImageIO.read(getClass().getResourceAsStream(path));
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
    }

    public String getName() {
        return name;
    }

    public BufferedImage getImage(int x, int y) {
        return image.getSubimage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight);
    }
}
