package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.game.Camera;
import fr.reminy.pokemon_discord.game.GameObject;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.img.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character extends GameObject {
    private final SpriteSheet spriteSheet;
    private Direction facingDirection = Direction.DOWN;

    public Character(int x, int y, SpriteSheet spriteSheet) {
        super(x, y);
        this.spriteSheet = spriteSheet;
    }

    public void moveLeft() {
        this.x--;
        facingDirection = Direction.LEFT;
    }

    public void moveRight() {
        this.x++;
        facingDirection = Direction.RIGHT;
    }

    public void moveUp() {
        this.y--;
        facingDirection = Direction.UP;
    }

    public void moveDown() {
        this.y++;
        facingDirection = Direction.DOWN;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage currentFrame = spriteSheet.getImage(0, facingDirection.ordinal());
        graphics2D.drawImage(currentFrame, getAbsX(), getAbsY()-10, null);
    }

    @Override
    public void update() {

    }
}
