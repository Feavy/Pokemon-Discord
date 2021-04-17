package fr.reminy.pokemon_discord.game;

import org.javacord.api.entity.message.Message;

import java.util.HashMap;
import java.util.Map;

public class GameManager extends HashMap<Long, PokemonGame> {
    public static final GameManager INSTANCE = new GameManager();
    private final Map<Message, PokemonGame> messageGameMap = new HashMap<>();
    private final Map<PokemonGame, Message> gameMessageMap = new HashMap<>();
    private GameManager() {
    }

    public void setLinkedMessage(Message msg, PokemonGame finalPlayerGame) {
        Message message = gameMessageMap.get(finalPlayerGame);
        if (message != null) {
            messageGameMap.remove(message);
        }
        messageGameMap.put(msg, finalPlayerGame);
        gameMessageMap.put(finalPlayerGame, msg);
        finalPlayerGame.setLinkedMessage(msg);
    }

    public PokemonGame getGameByMessage(Message message) {
        return messageGameMap.get(message);
    }
}
