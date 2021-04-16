package fr.reminy.pokemon_discord.game;

import org.javacord.api.entity.message.Message;

import java.util.HashMap;
import java.util.Map;

public class GameManager extends HashMap<Long, PokemonGame>{
    public static final GameManager INSTANCE = new GameManager();

    private GameManager() { }

    private final Map<Message, PokemonGame> messageGameMap = new HashMap<>();

    public void setLinkedMessage(Message msg, PokemonGame finalPlayerGame) {
        messageGameMap.put(msg, finalPlayerGame);
    }

    public PokemonGame getGameByMessage(Message message) {
        return messageGameMap.get(message);
    }
}
