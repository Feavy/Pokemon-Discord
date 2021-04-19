package fr.reminy.pokemon_discord.game.maps;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.data.CollisionType;
import fr.reminy.pokemon_discord.game.data.Event;
import fr.reminy.pokemon_discord.game.data.Events;
import fr.reminy.pokemon_discord.game.data.Position;
import fr.reminy.pokemon_discord.game.data.TileType;
import fr.reminy.pokemon_discord.game.entity.Character;
import fr.reminy.pokemon_discord.game.render.Drawable;
import fr.reminy.pokemon_discord.tmx.ClasspathTMXMapReader;
import org.mapeditor.core.Properties;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.view.OrthogonalRenderer;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Map implements Drawable {
    // MAPS
    private final static HashMap<String, Map> maps = new HashMap<>();

    public final static Map BOURG_PEPIN = register("Bourg Pépin", "/maps/map infini.tmx");
    public final static Map RED_HOUSE = register("Red's house", "/maps/red_house.tmx");


    private final String name;
    private final org.mapeditor.core.Map map;
    private final Set<Character> characters = new HashSet<>();
    private final int id;

    public Map(String name, org.mapeditor.core.Map map, int id) {
        this.name = name;
        this.map = map;
        this.id = id;
    }

    private static Map register(String name, String path) {
        ClasspathTMXMapReader reader = new ClasspathTMXMapReader();
        org.mapeditor.core.Map map = null;
        try {
            map = reader.readMap(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int id = maps.size();
        Map m = new Map(name, map, id);
        maps.put(name, m);
        return m;
    }

    public static Map fromName(String map) {
        return maps.get(map);
    }

    public String getName() {
        return name;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public TileLayer getLayer(int index) {
        return (TileLayer) map.getLayer(index);
    }

    public Event getEventAt(Position position) {
        Tile tile = getLayer(Settings.ID_EVENTS).getTileAt(position.getX(), position.getY());
        int tileId = tile.getId();
        Properties properties = tile.getProperties();
        return Events.fromProperties(tileId, properties);
    }

    public TileType getTileType(int x, int y) {
        Tile tile = getLayer(Settings.ID_COLLISIONS).getTileAt(x, y);
        if (tile == null) {
            return TileType.TILE_C;
        }
        return TileType.getTileTypeById(tile.getId());
    }

    public TileType getTileType(Position position) {
        return getTileType(position.getX(), position.getY());
    }

    public CollisionType getCollisionType(int x, int y, int h) {
        return getTileType(x, y).getCollisionType(h);
    }

    public CollisionType getCollisionType(Position position) {
        return getCollisionType(position.getX(), position.getY(), position.getH());
    }

    public int getTileSize() {
        return map.getTileWidth();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        OrthogonalRenderer orthogonalRenderer = new OrthogonalRenderer(map);

        // Draw map layer 0 & 1
        orthogonalRenderer.paintTileLayer(graphics2D, (TileLayer) map.getLayer(0));
        orthogonalRenderer.paintTileLayer(graphics2D, (TileLayer) map.getLayer(1));

        // draw character
        characters.stream()
                .filter(c -> c.getLocation().getH() == 1)
                .forEach(c -> c.draw(graphics2D));

        // Draw map layer 2
        orthogonalRenderer.paintTileLayer(graphics2D, (TileLayer) map.getLayer(2));
        characters.stream()
                .filter(c -> c.getLocation().getH() > 1)
                .forEach(c -> c.draw(graphics2D));

    }
}
