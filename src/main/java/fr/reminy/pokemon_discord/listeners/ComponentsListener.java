package fr.reminy.pokemon_discord.listeners;

import fr.reminy.pokemon_discord.components.buttons.Buttons;
import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

public class ComponentsListener implements MessageComponentCreateListener {
    @Override
    public void onComponentCreate(MessageComponentCreateEvent event) {
        MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();
        String customId = messageComponentInteraction.getCustomId();
        System.out.println("Test " + customId);

        Buttons.get(customId).onClick(event);
        System.out.println("Fin on click");
    }
}
