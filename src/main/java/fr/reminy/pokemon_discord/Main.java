package fr.reminy.pokemon_discord;

import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.listeners.CommandListener;
import fr.reminy.pokemon_discord.listeners.ReactionListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(args[0])
                .login().join();

        api.updateActivity(ActivityType.COMPETING, "a!help");

        CommandListener commandListener = new CommandListener();
        api.addMessageCreateListener(commandListener);
        api.addMessageEditListener(commandListener);

        ReactionListener reactionListener = new ReactionListener();
        api.addReactionAddListener(reactionListener);
        api.addReactionRemoveListener(reactionListener);

        GameHttpServer.INSTANCE.start();
    }
}
