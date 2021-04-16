package fr.reminy.pokemon_discord.command;

import fr.reminy.pokemon_discord.command.commands.AdventureCommand;
import fr.reminy.pokemon_discord.command.commands.HelpCommand;
import fr.reminy.pokemon_discord.command.commands.PingCommand;

public enum Commands {
    PING_COMMAND(new PingCommand()),
    HELP_COMMAND(new HelpCommand()),
    ADVENTURE_COMMAND(new AdventureCommand());

    private final Command command;

    Commands(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
