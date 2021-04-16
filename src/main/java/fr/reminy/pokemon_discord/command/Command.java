package fr.reminy.pokemon_discord.command;

import org.javacord.api.entity.channel.TextChannel;

import java.util.List;

public interface Command {
    String[] getLabels();
    String getDescription();
    Category getCategory();
    void execute(TextChannel channel, List<String> args);
}
