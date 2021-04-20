package fr.reminy.pokemon_discord.game.map.event;

import fr.reminy.pokemon_discord.game.map.event.events.Warp;
import org.mapeditor.core.Properties;

import java.util.function.Function;

public enum Events {
    WARP(Warp::fromProperties)
;
    private final Function<Properties, Event> fromPropertiesFunction;

    Events(Function<Properties, Event> fromPropertiesFunction) {
        this.fromPropertiesFunction = fromPropertiesFunction;
    }

    public static Event fromProperties(int id, Properties properties) {
        return values()[id].fromPropertiesFunction.apply(properties);
    }
}
