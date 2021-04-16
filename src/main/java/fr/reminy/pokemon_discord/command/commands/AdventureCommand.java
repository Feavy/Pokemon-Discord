package fr.reminy.pokemon_discord.command.commands;

import fr.reminy.pokemon_discord.command.Category;
import fr.reminy.pokemon_discord.command.Command;
import org.javacord.api.entity.channel.TextChannel;

import java.util.List;

public class AdventureCommand implements Command {
    @Override
    public String[] getLabels() {
        return new String[]{"aventure", "a"};
    }

    @Override
    public String getDescription() {
        return "Démarrer ou reprendre une aventure Pokémon !";
    }

    @Override
    public Category getCategory() {
        return Category.GAME;
    }

    @Override
    public void execute(TextChannel channel, List<String> args) {
        channel.sendMessage("C'est parti !");
    }
}
