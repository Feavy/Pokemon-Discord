package fr.reminy.pokemon_discord.game.data;

public class Position implements Cloneable {
    /**
     * Abscisse en tiles
     */
    private int x;
    /**
     * Ordonnée en tiles
     */
    private int y;
    /**
     * Hauteur (layer)
     */
    private int h;

    public Position(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public Position(int x, int y) {
        this(x, y, 0);
    }

    public Position add(int x, int y, int h) {
        this.x += x;
        this.y += y;
        this.h += h;
        return this;
    }

    public Position add(Position position) {
        return this.add(position.x, position.y, position.h);
    }

    public Position add(int x, int y) {
        return this.add(x, y, 0);
    }

    /**
     * Abscisse en tiles
     */
    public int getX() {
        return x;
    }

    /**
     * Abscisse en pixels
     */
    public int getAbsX() {
        return x * 16;
    }

    /**
     * Ordonnée en tiles
     */
    public int getY() {
        return y;
    }

    /**
     * Ordonnée en pixels
     */
    public int getAbsY() {
        return y * 16;
    }

    /**
     * Hauteur (layer)
     */
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    /**
     * Hauteur (layer)
     */
    public int getHeight() {
        return h;
    }

    public Position set(Position position) {
        this.x = position.x;
        this.y = position.y;
        this.h = position.h;
        return this;
    }

    @Override
    public Position clone() {
        return new Position(x, y, h);
    }
}
