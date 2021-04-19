package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.game.GameObject;
import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.data.Position;
import fr.reminy.pokemon_discord.game.data.TileType;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.img.SpriteSheet;
import fr.reminy.pokemon_discord.game.maps.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.reminy.pokemon_discord.game.data.CollisionType.AIR;

public class Character extends GameObject {
    private final SpriteSheet spriteSheet;
    private Direction facingDirection = Direction.DOWN;
    private int height = 1;

    public Character(Location location, SpriteSheet spriteSheet) {
        super(location);
        this.spriteSheet = spriteSheet;
    }

    @Override
    public Location getPosition() {
        return (Location) super.getPosition();
    }

    public Location getLocation() {
        return getPosition();
    }

    public int getHeight() {
        return height;
    }

    public void move(Direction direction) {
        changeDirection(direction);
        Location destination = getLocation().clone().add(direction.toPosition());

        if(destination.getCollisionType() == AIR) {
            getLocation().add(direction.toPosition());
            TileType type = destination.getTileType();
            if(type == TileType.TILE_12) {
                getLocation().setH(2);
            } else if(type == TileType.TILE_21) {
                getLocation().setH(1);
            }
        }
    }

    public void changeDirection(Direction direction) {
        facingDirection = direction;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        BufferedImage currentFrame = spriteSheet.getImage(0, facingDirection.ordinal());
        graphics2D.drawImage(currentFrame, getAbsX(), getAbsY() - 5, null);
    }

    @Override
    public void update() {

    }
}
