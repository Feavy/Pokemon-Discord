package fr.reminy.pokemon_discord.game.data;

import static fr.reminy.pokemon_discord.game.data.CollisionType.AIR;
import static fr.reminy.pokemon_discord.game.data.CollisionType.BLOCK;

public enum TileType {
    TILE_1(BLOCK, BLOCK),
    TILE_1C(BLOCK, AIR),
    TILE_C1(AIR, BLOCK),
    TILE_12(AIR, AIR),
    TILE_21(AIR, AIR),
    TILE_C(AIR, AIR);

    private final CollisionType collisionTypeLayer1;
    private final CollisionType collisionTypeLayer2;

    TileType(CollisionType collisionTypeLayer1, CollisionType collisionTypeLayer2) {
        this.collisionTypeLayer1 = collisionTypeLayer1;
        this.collisionTypeLayer2 = collisionTypeLayer2;
    }

    public static TileType getTileTypeById(Integer id) {
        if(id == null || id >= TileType.values().length) {
            return TileType.TILE_1;
        }
        return TileType.values()[id];
    }

    public CollisionType getCollisionType(int h) {
        if(h == 1) {
            return collisionTypeLayer1;
        }else if(h == 2){
            return collisionTypeLayer2;
        }else {
            return BLOCK;
        }
    }
}
