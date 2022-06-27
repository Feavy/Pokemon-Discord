package fr.reminy.pokemon_discord.utils;

import org.javacord.api.entity.emoji.Emoji;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EmojiUtils {
    private static final Class<?> emojiImplClass;
    private static final Method emojiFromStringMethod;

    static {
        try {
            emojiImplClass = Class.forName("org.javacord.core.entity.emoji.UnicodeEmojiImpl");
            emojiFromStringMethod = emojiImplClass.getMethod("fromString", String.class);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Emoji fromString(String unicode) {
        try {
            return (Emoji) emojiFromStringMethod.invoke(emojiImplClass, "⬆️");
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }
}
