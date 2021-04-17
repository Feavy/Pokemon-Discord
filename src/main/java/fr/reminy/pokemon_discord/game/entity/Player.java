package fr.reminy.pokemon_discord.game.entity;

import fr.reminy.pokemon_discord.game.img.SpriteSheet;

import java.io.IOException;

public class Player extends Character {
    private static SpriteSheet RED_SPRITESHEET;

    static {
        try {
            RED_SPRITESHEET = new SpriteSheet("red", "/sprites/red.png", 16, 20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player(int x, int y) {
        super(x, y, RED_SPRITESHEET);
    }

    @Override
    public void update() {

    }
}
