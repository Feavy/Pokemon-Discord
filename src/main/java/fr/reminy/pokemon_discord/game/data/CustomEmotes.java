package fr.reminy.pokemon_discord.game.data;

import fr.reminy.pokemon_discord.PokemonDiscord;
import org.javacord.api.entity.emoji.Emoji;

import static fr.reminy.pokemon_discord.PokemonDiscord.discordAPI;

public enum CustomEmotes {
    A("990847189523763200"),
    B("990847191096647740"),
    LEFT("990847183362334721"),
    UP("990847185262358578"),
    DOWN("990847186281594892"),
    RIGHT("990847188143849492"),
    ;

    private final String id;

    CustomEmotes(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Emoji toEmoji() {
        return discordAPI().getCustomEmojiById(id).orElseThrow();
    }
}
