package fr.reminy.pokemon_discord.game.data;

public enum Emotes {
    A(832749665735933993L),
    B(832748285835870228L);

    private final long id;

    Emotes(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
