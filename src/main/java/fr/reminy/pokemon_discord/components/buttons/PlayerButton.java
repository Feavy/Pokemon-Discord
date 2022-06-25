package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.components.SimplePokemonGameCallback;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;

import java.util.function.Consumer;

class PlayerButton extends SimpleButton {
    private final Consumer<Player> callback;

    public PlayerButton(String id, String label, String emoji, ButtonStyle style, Consumer<Player> callback) {
        super(id, label, emoji, style);
        this.callback = callback;
    }

    public PlayerButton(String id, String label, ButtonStyle style, Consumer<Player> callback) {
        super(id, label, style);
        this.callback = callback;
    }

    @Override
    public void onClick(MessageComponentCreateEvent event) {
        new SimplePokemonGameCallback(game -> callback.accept(game.getPlayer())).accept(event);
    }
}

