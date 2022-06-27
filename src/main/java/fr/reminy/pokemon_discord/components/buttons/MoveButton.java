package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.message.component.ButtonStyle;

class MoveButton extends AbstractButton {
    private final Direction direction;

    public MoveButton(Direction direction) {
        super("Button_" + direction.name(), "", direction.asEmoji(), ButtonStyle.PRIMARY);
        this.direction = direction;
    }

    @Override
    public void onClick(Player player) {
        player.move(direction);
        player.game().update();
    }
}

