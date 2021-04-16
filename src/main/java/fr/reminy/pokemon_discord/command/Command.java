package fr.reminy.pokemon_discord.command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.List;

public interface Command {
    String[] getLabels();
    String getDescription();
    Category getCategory();
    void execute(MessageCreateEvent event, TextChannel channel, List<String> args);
}
