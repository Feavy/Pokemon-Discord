package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.game.GameObject;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.data.TileType;
import fr.reminy.pokemon_discord.game.img.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.reminy.pokemon_discord.game.data.CollisionType.AIR;

public class Character extends GameObject {
    public final int OFFSET = 5;

    private final SpriteSheet spriteSheet;
    private Direction facingDirection = Direction.DOWN;

    public Character(Location location, SpriteSheet spriteSheet) {
        super(location);
        this.spriteSheet = spriteSheet;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    @Override
    public Location getPosition() {
        return (Location) super.getPosition();
    }

    public Location getLocation() {
        return getPosition();
    }

    public boolean move(Direction direction) {
        changeDirection(direction);
        Location destination = getLocation().clone().add(direction.getOffset());

        if (destination.getCollisionType() == AIR) {
            getLocation().add(direction.getOffset());
            TileType type = destination.getTileType();
            if (type == TileType.TILE_12) {
                getLocation().setH(2);
            } else if (type == TileType.TILE_21) {
                getLocation().setH(1);
            }
            return true;
        } else {
            return false;
        }
    }

    public void changeDirection(Direction direction) {
        facingDirection = direction;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage currentFrame = spriteSheet.getImage(0, facingDirection.ordinal());
        graphics2D.drawImage(currentFrame, getAbsX(), getAbsY() - OFFSET, null);
    }

    @Override
    public void update() {

    }
}
