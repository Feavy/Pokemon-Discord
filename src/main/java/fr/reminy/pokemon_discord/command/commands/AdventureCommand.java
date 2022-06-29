package fr.reminy.pokemon_discord.command.commands;

import fr.reminy.pokemon_discord.annotation.command.Register;
import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import fr.reminy.pokemon_discord.components.buttons.Buttons;
import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.data.Location;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.game.map.Map;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.ActionRow;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageEvent;

import java.awt.image.BufferedImage;
import java.util.List;

@Register
public class AdventureCommand implements Command {
    @Override
    public String[] getLabels() {
        return new String[]{"aventure", "a"};
    }

    @Override
    public String getDescription() {
        return "Démarrer ou reprendre une aventure Pokémon !";
    }

    @Override
    public Category getCategory() {
        return Category.GAME;
    }

    @Override
    public void execute(MessageEvent event, MessageAuthor author, TextChannel channel, List<String> args) {
        if (author.asUser().isEmpty()) {
            throw new RuntimeException("This command can only be executed by an user.");
        }

        User user = author.asUser().get();
        long userId = user.getId();
        PokemonGame playerGame = GameManager.INSTANCE.get(userId);
        if (playerGame == null) {
            playerGame = new PokemonGame(user, new Player(user.getName(), new Location(29, 16, 1, Map.BOURG_PEPIN)));
            GameManager.INSTANCE.put(userId, playerGame);
        }

        BufferedImage rendered = playerGame.getRenderer().render();
        String playerImageURL = GameHttpServer.INSTANCE.setPlayerImage(userId, rendered);

        PokemonGame finalPlayerGame = playerGame;

        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder
                .setContent(playerImageURL)
                .addComponents(ActionRow.of(Buttons.A, Buttons.UP, Buttons.B, Buttons.VITESSE_MINUS),
                        ActionRow.of(Buttons.LEFT, Buttons.DOWN, Buttons.RIGHT, Buttons.VITESSE_PLUS))
                .send(channel).thenAccept(msg -> GameManager.INSTANCE.setLinkedMessage(msg, finalPlayerGame));
    }
}
