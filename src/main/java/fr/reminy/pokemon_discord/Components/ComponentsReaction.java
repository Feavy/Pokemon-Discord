package fr.reminy.pokemon_discord.Components;

import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.DiscordApi;
import org.javacord.api.interaction.MessageComponentInteraction;

public class ComponentsReaction {

    public ComponentsReaction(DiscordApi api) {

        api.addMessageComponentCreateListener(event -> {
            MessageComponentInteraction messageComponentInteraction = event.getMessageComponentInteraction();
            String customId = messageComponentInteraction.getCustomId();

            switch (customId) {
                case "Vit-":
                    messageComponentInteraction.getMessage().ifPresent(msg -> {
                        Player player = GameManager.INSTANCE.getGameByMessage(msg).getPlayer();
                        player.slower();
                        messageComponentInteraction.createImmediateResponder()
                                .respond();
                    });
                    break;

                case "Vit+":
                    messageComponentInteraction.getMessage().ifPresent(msg -> {
                        Player player = GameManager.INSTANCE.getGameByMessage(msg).getPlayer();
                        player.faster();
                        messageComponentInteraction.createImmediateResponder()
                                .respond();
                    });
                    break;
            }
        });
    }
}
