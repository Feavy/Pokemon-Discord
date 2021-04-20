package fr.reminy.pokemon_discord.game.data;

import org.javacord.api.entity.emoji.Emoji;

public enum Direction {
    UP(new Position(0, -1)),
    RIGHT(new Position(1, 0)),
    DOWN(new Position(0, 1)),
    LEFT(new Position(-1, 0));

    private final Position offset;

    Direction(Position offset) {
        this.offset = offset;
    }

    public Position getOffset() {
        return offset;
    }

    public static Direction fromEmoji(Emoji emoji) {
        if (emoji.equalsEmoji("⬅️")) {
            return Direction.LEFT;
        } else if (emoji.equalsEmoji("⬆️")) {
            return Direction.UP;
        } else if (emoji.equalsEmoji("⬇️")) {
            return Direction.DOWN;
        } else if (emoji.equalsEmoji("➡️")) {
            return Direction.RIGHT;
        } else {
            return null;
        }
    }
}
