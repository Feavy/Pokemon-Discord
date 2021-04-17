package fr.reminy.pokemon_discord.command;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageEvent;

import java.util.List;

public interface Command {
    String[] getLabels();

    String getDescription();

    Category getCategory();

    void execute(MessageEvent event, MessageAuthor author, TextChannel channel, List<String> args);
}
