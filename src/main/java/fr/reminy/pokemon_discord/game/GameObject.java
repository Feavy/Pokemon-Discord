package fr.reminy.pokemon_discord.game;

import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.data.Position;
import fr.reminy.pokemon_discord.game.render.Drawable;

import java.awt.*;

public abstract class GameObject implements Drawable {
    private Position position;

    public GameObject(Position position) {
        this.position = position;
    }

    public GameObject(int x, int y) {
        this(new Position(x, y));
    }

    public Position getPosition() {
        return position;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getAbsX() {
        return position.getAbsX();
    }

    public int getAbsY() {
        return position.getAbsY();
    }

    public abstract void draw(Graphics2D graphics2D);

    public abstract void update();
}
