package fr.reminy.pokemon_discord.game.render;

import fr.reminy.pokemon_discord.game.GameObject;

import java.awt.*;

public class Camera extends GameObject {
    private final int width;
    private final int height;
    private GameObject toFollow;

    public Camera(GameObject toFollow, int width, int height) {
        super(toFollow.getX(), toFollow.getY());
        this.toFollow = toFollow;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getAbsWidth() {
        return width * 16;
    }

    public int getAbsHeight() {
        return height * 16;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
    }

    @Override
    public void update() {
        getPosition().set(toFollow.getPosition());
    }
}
