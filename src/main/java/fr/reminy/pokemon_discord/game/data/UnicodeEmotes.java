package fr.reminy.pokemon_discord.game.data;

import fr.reminy.pokemon_discord.PokemonDiscord;
import org.javacord.api.entity.emoji.Emoji;

public enum UnicodeEmotes {
    LEFT("??"),
    UP("??"),
    DOWN("??"),
    RIGHT("??"),
    A("?"),
    B("?"),
    ;

    private final String unicode;

    UnicodeEmotes(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }

}
