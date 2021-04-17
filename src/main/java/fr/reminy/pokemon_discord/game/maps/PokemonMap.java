package fr.reminy.pokemon_discord.game.maps;

import fr.reminy.pokemon_discord.tmx.ClasspathTMXMapReader;
import org.mapeditor.core.Map;

public enum PokemonMap {
    BOURG_PEPIN("Bourg P�pin", "/maps/map1.tmx");

    private final String name;
    private final Map map;

    PokemonMap(String name, String path) {
        this.name = name;

        ClasspathTMXMapReader reader = new ClasspathTMXMapReader();
        Map map = null;
        try {
            map = reader.readMap(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
    }
}
