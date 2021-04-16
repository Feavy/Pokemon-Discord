package fr.reminy.pokemon_discord;

import fr.reminy.pokemon_discord.command.CommandManager;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class Main {
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(args[0])
                .login().join();

        CommandManager commandManager = new CommandManager();
        commandManager.registerCommands(api);
    }
}
