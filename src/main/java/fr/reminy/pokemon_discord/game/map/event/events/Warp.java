package fr.reminy.pokemon_discord.game.map.event.events;

import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.entity.Character;
import fr.reminy.pokemon_discord.game.map.Map;
import fr.reminy.pokemon_discord.game.map.event.Event;
import org.mapeditor.core.Properties;
import org.mapeditor.core.Property;

public class Warp implements Event {

    private final Location destination;

    public Warp(Location destination) {
        this.destination = destination;
    }

    public void executeOn(Character character) {
        character.teleportTo(destination);
    }

    public static Warp fromProperties(Properties properties) {
        Map map = Map.fromName(properties.getProperty("map"));
        for (Property p : properties.getProperties()) {
            System.out.println(p.getName());
        }
        if (map == null) {
            return null;
        }
        return new Warp(new Location(
                Integer.parseInt(properties.getProperty("x", "0")),
                Integer.parseInt(properties.getProperty("y", "0")),
                Integer.parseInt(properties.getProperty("h_destination", "1")),
                map));
    }
}