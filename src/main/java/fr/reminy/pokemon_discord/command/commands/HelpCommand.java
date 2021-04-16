package fr.reminy.pokemon_discord.command.commands;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import fr.reminy.pokemon_discord.command.Commands;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<Category, List<Command>> commands = Arrays.stream(Commands.values())
                .map(Commands::getCommand)
                .collect(Collectors.groupingBy(Command::getCategory));

        System.out.println(commands);

        EmbedBuilder embedBuilder = new EmbedBuilder();

        for (Map.Entry<Category, List<Command>> commandsByCategory : commands.entrySet()) {
            System.out.println(commandsByCategory);
            Category category = commandsByCategory.getKey();
            List<Command> cmds = commandsByCategory.getValue();
            embedBuilder.addField(category.getLabel().toUpperCase(),
                    cmds.stream().map(cmd -> String.format("`%s` : %s", Settings.PREFIX+cmd.getLabels()[0], cmd.getDescription()))
                    .collect(Collectors.joining("\n")));
        }

        channel.sendMessage(embedBuilder);
    }
}
