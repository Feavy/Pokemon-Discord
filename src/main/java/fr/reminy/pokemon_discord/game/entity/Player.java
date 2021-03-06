package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.PokemonGame;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.img.SpriteSheet;

import java.awt.*;
import java.io.IOException;

public class Player extends Character {
    private static SpriteSheet RED_SPRITESHEET;

    static {
        try {
            RED_SPRITESHEET = new SpriteSheet("red", "/sprites/red.png", 16, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String username;
    private int speed = 1;
    private PokemonGame game;

    public Player(String username, Location location) {
        super(location, RED_SPRITESHEET);
        this.username = username;
        location.getMap().addCharacter(this);
    }

    public void setGame(PokemonGame pokemonGame) {
        this.game = pokemonGame;
    }

    public PokemonGame game() {
        return game;
    }

    @Override
    public void update() {

    }

    private int usernameWidth = -1;
    private int usernameHeight = -1;

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
        if(usernameWidth == -1) {
            usernameWidth = graphics2D.getFontMetrics().stringWidth(username);
            usernameHeight = graphics2D.getFontMetrics().getHeight();
        }
        int x = getAbsX() + getSpriteSheet().getSpriteWidth() / 4 * Settings.SCALE_FACTOR - usernameWidth / 2;
        int y = getAbsY() - getSpriteSheet().getSpriteHeight() - OFFSET / 2;
        graphics2D.setColor(new Color(0f, 0f, 0f, 0.75f));
        graphics2D.fillRect(x - 1, y - 1, usernameWidth + 2, usernameHeight + 2);
        graphics2D.setColor(new Color(1f, 1f, 1f));
        graphics2D.drawString(username, x, y + usernameHeight - 2);
    }

    @Override
    public boolean move(Direction direction) {
        // TODO à déplacer dans Player
        for (int i = 0; i < speed; i++) {
            boolean moved = super.move(direction);

            if(!moved) {
                game.getLinkedMessage().ifPresent(msg -> msg.addReaction("⛔"));
                return false;
            }
        }
        game.getLinkedMessage().ifPresent(msg -> msg.removeReactionByEmoji("⛔"));
        return true;
    }

    public int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed = speed;
    }

    public int faster(int add) {
        setSpeed(getSpeed() + add);
        return getSpeed();
    }

    public int faster() {
        return faster(1);
    }

    public int slower(int sub) {
        setSpeed(Math.max(getSpeed() - sub, 1));
        return getSpeed();
    }

    public int slower() {
        return slower(1);
    }
}
