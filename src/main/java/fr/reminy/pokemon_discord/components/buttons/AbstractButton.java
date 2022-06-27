package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;

public abstract class AbstractButton {
    public final String id;
    public final Button internal;

    public AbstractButton(String id, String label, Emoji emoji, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label, emoji);
        System.out.println(id);
    }

    public AbstractButton(String id, String label, String unicodeEmoji, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label, unicodeEmoji);
    }

    public AbstractButton(String id, String label, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label);
    }

    public abstract void onClick(Player player);
}
