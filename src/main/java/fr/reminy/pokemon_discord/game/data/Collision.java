package fr.reminy.pokemon_discord.game.data;

public enum Collision {

    TILE_1(0L),
    TILE_1C(1L),
    TILE_C1(2L),
    TILE_12(3L),
    TILE_21(4L);

    long id;

    Collision(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static Collision getCollisionById(long id) {
        for (Collision collision : values()) {
            if (collision.getId() == id) {
                return collision;
            }
        }
        return null;
    }
}
