package fr.reminy.pokemon_discord.game.data;

import fr.reminy.pokemon_discord.game.map.Map;
import fr.reminy.pokemon_discord.game.map.event.Event;
import fr.reminy.pokemon_discord.game.map.tile.CollisionType;
import fr.reminy.pokemon_discord.game.map.tile.TileType;

public class Location extends Position {

    private Map map;

    public Location(int x, int y, int h, Map map) {
        super(x, y, h);
        this.map = map;
    }

    public Location(int x, int y, Map map) {
        super(x, y);
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public Location add(int x, int y, int h) {
        super.add(x, y, h);
        return this;
    }

    public Location add(Position position) {
        super.add(position);
        return this;
    }

    public Location add(int x, int y) {
        return this.add(x, y, 0);
    }

    @Override
    public Location clone() {
        return new Location(getX(), getY(), getH(), map);
    }

    public TileType getTileType() {
        return map.getTileType(this);
    }

    public CollisionType getCollisionType() {
        return map.getCollisionType(this);
    }

    public Location set(Location position) {
        super.set(position);
        this.map = position.getMap();
        return this;
    }

    public Event getEvent() {
        return map.getEventAt(this);
    }
}
