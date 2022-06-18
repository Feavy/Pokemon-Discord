package fr.reminy.pokemon_discord.Components;

import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
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
                        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(msg);
                        if (playerGame == null) {
                            return;
                        }
                        if (!playerGame.canPlay(messageComponentInteraction.getUser())) {
                            return;
                        }

                        playerGame.getPlayer().slower();
                        messageComponentInteraction.createImmediateResponder()
                                .respond();
                    });
                    break;

                case "Vit+":
                    messageComponentInteraction.getMessage().ifPresent(msg -> {
                        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(msg);
                        if (playerGame == null) {
                            return;
                        }
                        if (!playerGame.canPlay(messageComponentInteraction.getUser())) {
                            return;
                        }

                        playerGame.getPlayer().faster();
                        messageComponentInteraction.createImmediateResponder()
                                .respond();
                    });
                    break;
            }
        });
    }
}
