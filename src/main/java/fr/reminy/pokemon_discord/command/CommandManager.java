package fr.reminy.pokemon_discord.command;

import fr.reminy.pokemon_discord.Settings;
import org.javacord.api.DiscordApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {
    public void registerCommands(DiscordApi api) {
        api.addMessageCreateListener(event -> {
            List<String> args = new ArrayList<>(Arrays.asList(event.getMessageContent().split("\\s+")));
            for(Commands command : Commands.values()) {
                Command cmd = command.getCommand();
                for (String label : cmd.getLabels()) {
                    String cmdLbl = Settings.PREFIX + label;
                    if (args.get(0).equalsIgnoreCase(cmdLbl)) {
                        args.remove(0);
                        cmd.execute(event, event.getChannel(), args);
                        return;
                    }
                }

            }
        });
    }
}
