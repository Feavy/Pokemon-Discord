package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.components.SimplePokemonGameCallback;
import fr.reminy.pokemon_discord.game.data.Direction;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;

class MoveButton extends SimpleButton {
    private final Direction direction;

    public MoveButton(Direction direction) {
        super("Button_" + direction.name(), "", direction.asEmoji(), ButtonStyle.SECONDARY);
        this.direction = direction;
    }

    @Override
    public void onClick(MessageComponentCreateEvent event) {
        new SimplePokemonGameCallback(playerGame -> {
            playerGame.getPlayer().move(direction);
            playerGame.update();
        }).accept(event);
    }
}

