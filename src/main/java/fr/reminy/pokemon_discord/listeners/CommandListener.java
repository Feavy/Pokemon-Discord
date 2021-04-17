package fr.reminy.pokemon_discord.listeners;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.command.Command;
import fr.reminy.pokemon_discord.command.Commands;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.message.MessageEditEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.message.MessageEditListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListener implements MessageCreateListener, MessageEditListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        List<String> args = new ArrayList<>(Arrays.asList(event.getMessageContent().split("\\s+")));
        for (Commands command : Commands.values()) {
            Command cmd = command.getCommand();
            for (String label : cmd.getLabels()) {
                String cmdLbl = Settings.PREFIX + label;
                if (args.get(0).equalsIgnoreCase(cmdLbl)) {
                    args.remove(0);
                    cmd.execute(event, event.getMessageAuthor(), event.getChannel(), args);
                    return;
                }
            }
        }
    }

    @Override
    public void onMessageEdit(MessageEditEvent event) {
        if (event.getMessageAuthor().isEmpty()) {
            return;
        }
        List<String> args = new ArrayList<>(Arrays.asList(event.getNewContent().split("\\s+")));
        for (Commands command : Commands.values()) {
            Command cmd = command.getCommand();
            for (String label : cmd.getLabels()) {
                String cmdLbl = Settings.PREFIX + label;
                if (args.get(0).equalsIgnoreCase(cmdLbl)) {
                    args.remove(0);
                    cmd.execute(event, event.getMessageAuthor().get(), event.getChannel(), args);
                    return;
                }
            }
        }
    }

}
