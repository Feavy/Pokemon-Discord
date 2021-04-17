package fr.reminy.pokemon_discord.game;

import java.awt.*;

public abstract class GameObject {
    /**
     * Abscisse en tiles
     */
    protected int x;

    /**
     * Ordonnée en tiles
     */
    protected int y;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAbsX() {
        return x * 16;
    }

    public abstract void draw(Graphics2D graphics2D);

    public int getAbsY() {
        return y * 16;
    }

    public abstract void update();
}
