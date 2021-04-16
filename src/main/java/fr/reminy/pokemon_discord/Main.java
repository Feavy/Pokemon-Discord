package fr.reminy.pokemon_discord;

import fr.reminy.pokemon_discord.command.CommandManager;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.listeners.ReactionListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(args[0])
                .login().join();

        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands(api);

        ReactionListener reactionListener = new ReactionListener();

        api.addReactionAddListener(reactionListener);
        api.addReactionRemoveListener(reactionListener);

        GameHttpServer.INSTANCE.start();
    }
}
