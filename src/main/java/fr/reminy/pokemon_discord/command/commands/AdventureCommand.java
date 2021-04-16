package fr.reminy.pokemon_discord.command.commands;

import com.vdurmont.emoji.EmojiParser;
import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.maps.PokemonMap;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.image.BufferedImage;
import java.util.List;

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
    public void execute(MessageCreateEvent event, TextChannel channel, List<String> args) {
        if(event.getMessageAuthor().asUser().isEmpty()) {
            throw new RuntimeException("This command can only be executed by an user.");
        }

        User user = event.getMessageAuthor().asUser().get();
        long userId = user.getId();
        PokemonGame playerGame = GameManager.INSTANCE.get(userId);
        if(playerGame == null) {
            playerGame = new PokemonGame(user, new Player(0,0), PokemonMap.BOURG_PEPIN);
            GameManager.INSTANCE.put(userId, playerGame);
        }

        BufferedImage rendered = playerGame.getRenderer().render();
        String playerImageURL = GameHttpServer.INSTANCE.setPlayerImage(userId, rendered);

        PokemonGame finalPlayerGame = playerGame;
        playerGame.getLinkedMessage().ifPresentOrElse(msg -> msg.edit(playerImageURL),
                () -> channel.sendMessage(playerImageURL).thenAccept(msg -> {
                    GameManager.INSTANCE.setLinkedMessage(msg, finalPlayerGame);
                    finalPlayerGame.setLinkedMessage(msg);
                    msg.addReaction(EmojiParser.parseToUnicode(":arrow_up:"));
                    msg.addReaction(EmojiParser.parseToUnicode(":arrow_right:"));
                    msg.addReaction(EmojiParser.parseToUnicode(":arrow_down:"));
                    msg.addReaction(EmojiParser.parseToUnicode(":arrow_left:"));
                }));
    }
}
