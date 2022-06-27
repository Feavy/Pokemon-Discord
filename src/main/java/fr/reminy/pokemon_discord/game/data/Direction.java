package fr.reminy.pokemon_discord.game.data;

import fr.reminy.pokemon_discord.PokemonDiscord;
import fr.reminy.pokemon_discord.Settings;
import org.javacord.api.entity.emoji.Emoji;

import java.util.Arrays;

public enum Direction {
    UP(new Position(0, -1), "990847185262358578"),
    RIGHT(new Position(1, 0), "990847188143849492"),
    DOWN(new Position(0, 1), "990847186281594892"),
    LEFT(new Position(-1, 0), "990847183362334721");

    private final Position offset;
    private final Emoji emoji;

    Direction(Position offset, String emojiId) {
        this.offset = offset;
        this.emoji = PokemonDiscord.getInstance().getAPI().getCustomEmojiById(emojiId).orElseThrow();
    }

    public Position getOffset() {
        return offset;
    }

    public Emoji asEmoji() {
        return emoji;
    }

    public static Direction fromEmoji(Emoji emoji) {
        return Arrays.stream(Direction.values())
                .filter(d -> emoji.equalsEmoji(d.emoji))
                .findFirst()
                .orElse(null);
    }
}
