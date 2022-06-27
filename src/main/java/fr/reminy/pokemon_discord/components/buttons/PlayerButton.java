package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.components.SimplePokemonGameCallback;
import fr.reminy.pokemon_discord.game.data.CustomEmotes;
import fr.reminy.pokemon_discord.game.data.UnicodeEmotes;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;

import java.util.function.Consumer;

class PlayerButton extends SimpleButton {
    private final Consumer<Player> callback;

    public PlayerButton(String id, String label, CustomEmotes emote, ButtonStyle style, Consumer<Player> callback) {
        super(id, label, emote.toEmoji(), style);
        this.callback = callback;
    }

    public PlayerButton(String id, String label, UnicodeEmotes unicodeEmoji, ButtonStyle style, Consumer<Player> callback) {
        super(id, label, unicodeEmoji.getUnicode(), style);
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

