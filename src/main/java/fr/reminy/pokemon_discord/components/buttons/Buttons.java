package fr.reminy.pokemon_discord.components.buttons;

import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.data.CustomEmotes;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.message.component.Button;
import org.javacord.api.entity.message.component.ButtonStyle;

import java.util.Map;
import java.util.HashMap;

public class Buttons {

    private static final Map<String, SimpleButton> map = new HashMap<>();

    public static final Button LEFT = register(new MoveButton(Direction.LEFT));
    public static final Button UP = register(new MoveButton(Direction.UP));
    public static final Button DOWN = register(new MoveButton(Direction.DOWN));
    public static final Button RIGHT = register(new MoveButton(Direction.RIGHT));

    public static final Button A = register(new PlayerButton("A", "", CustomEmotes.A, ButtonStyle.SUCCESS, p -> {}));
    public static final Button B = register(new PlayerButton("B", "", CustomEmotes.B, ButtonStyle.DANGER, p -> {}));

    public static final Button VITESSE_MINUS = register(new PlayerButton("Vit-", "Vitesse-", ButtonStyle.SECONDARY, Player::slower));
    public static final Button VITESSE_PLUS = register(new PlayerButton("Vit+", "Vitesse+", ButtonStyle.SECONDARY, Player::faster));

    public static Button register(SimpleButton button) {
        map.put(button.id, button);
        return button.internal;
    }

    public static SimpleButton get(String label) {
        return map.get(label);
    }

    public static Map<String, SimpleButton> map() {
        return map;
    }

}
