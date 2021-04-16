package fr.reminy.pokemon_discord.command.commands;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import fr.reminy.pokemon_discord.command.Commands;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public String[] getLabels() {
        return new String[] {"help", "h"};
    }

    @Override
    public String getDescription() {
        return "Affiche la liste des commande et leur fonction.";
    }

    @Override
    public Category getCategory() {
        return Category.COMMON;
    }

    @Override
    public void execute(TextChannel channel, List<String> args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        for (Commands commands : Commands.values()) {
            Command cmd = commands.getCommand();
            embedBuilder.addField(Settings.PREFIX+cmd.getLabels()[0], cmd.getDescription());
        }
        channel.sendMessage(embedBuilder);
    }
}
