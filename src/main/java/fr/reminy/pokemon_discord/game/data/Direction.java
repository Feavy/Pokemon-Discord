package fr.reminy.pokemon_discord.game.data;

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
}
