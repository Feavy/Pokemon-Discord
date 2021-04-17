package fr.reminy.pokemon_discord.command.commands;

import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageEvent;

import java.util.List;

public class PingCommand implements Command {

    @Override
    public String[] getLabels() {
        return new String[]{"ping", "p"};
    }

    @Override
    public String getDescription() {
        return "Un simple ping.";
    }

    @Override
    public Category getCategory() {
        return Category.COMMON;
    }

    @Override
    public void execute(MessageEvent event, MessageAuthor author, TextChannel channel, List<String> args) {
        channel.sendMessage("Pong!");
    }
}
