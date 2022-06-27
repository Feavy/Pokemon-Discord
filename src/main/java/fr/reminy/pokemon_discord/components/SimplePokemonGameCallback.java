package fr.reminy.pokemon_discord.components;

import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;

import java.util.function.Consumer;

public class SimplePokemonGameCallback implements Consumer<MessageComponentCreateEvent> {
    private final Consumer<PokemonGame> callable;

    public SimplePokemonGameCallback(Consumer<PokemonGame> callable) {
        this.callable = callable;
    }

    @Override
    public void accept(MessageComponentCreateEvent event) {
        System.out.println("accepted");
        MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();

        Message msg = messageComponentInteraction.getMessage();
        messageComponentInteraction.getMessage();
        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(msg);
        System.out.println(playerGame);
        if (playerGame == null) {
            return;
        }
        System.out.println("respond");
        if (!playerGame.canPlay(messageComponentInteraction.getUser())) {
            return;
        }

        callable.accept(playerGame);

        System.out.println("respond");
        messageComponentInteraction.createImmediateResponder().respond();
    }
}
