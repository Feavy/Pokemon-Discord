package fr.reminy.pokemon_discord.game.data;

import org.javacord.api.entity.emoji.Emoji;

import java.util.Arrays;

public enum Direction {
    UP(new Position(0, -1), "⬆️"),
    RIGHT(new Position(1, 0), "➡️"),
    DOWN(new Position(0, 1), "⬇️"),
    LEFT(new Position(-1, 0), "⬅️");

    private final Position offset;
    private final String emoji;

    Direction(Position offset, String emoji) {
        this.offset = offset;
        this.emoji = emoji;
    }

    public Position getOffset() {
        return offset;
    }

    public String asEmoji() {
        return emoji;
    }

    public static Direction fromEmoji(Emoji emoji) {
        return Arrays.stream(Direction.values())
                .filter(d -> emoji.equalsEmoji(d.emoji))
                .findFirst()
                .orElse(null);
    }
}
