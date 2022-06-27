package fr.reminy.pokemon_discord.components.buttons;

import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;

public abstract class SimpleButton {
    public final String id;
    public final Button internal;

    public SimpleButton(String id, String label, Emoji emoji, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label, emoji);
        System.out.println(id);
    }

    public SimpleButton(String id, String label, String unicodeEmoji, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label, unicodeEmoji);
    }

    public SimpleButton(String id, String label, ButtonStyle style) {
        this.id = id;
        internal = Button.create(id, style, label);
    }

    public abstract void onClick(MessageComponentCreateEvent event);
}
