package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.game.GameObject;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.img.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends GameObject {
    private final SpriteSheet spriteSheet;
    private Direction facingDirection = Direction.DOWN;
    private int height = 1;

    public Character(int x, int y, SpriteSheet spriteSheet) {
        super(x, y);
        this.spriteSheet = spriteSheet;
    }

    public int getHeight() {
        return height;
    }

    public void move(Direction direction) {
        switch (direction) {
            case LEFT -> x--;
            case RIGHT -> x++;
            case UP -> y--;
            case DOWN -> y++;
        }
    }

    public void changeDirection(Direction direction) {
        facingDirection = direction;
    }

    public void changeHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage currentFrame = spriteSheet.getImage(0, facingDirection.ordinal());
        graphics2D.drawImage(currentFrame, getAbsX(), getAbsY() - 10, null);
    }

    @Override
    public void update() {

    }
}
