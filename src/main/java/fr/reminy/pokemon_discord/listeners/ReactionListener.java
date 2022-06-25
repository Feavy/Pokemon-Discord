package fr.reminy.pokemon_discord.listeners;

import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.entity.Player;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.Reaction;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.event.message.reaction.SingleReactionEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class ReactionListener implements ReactionAddListener, ReactionRemoveListener {
    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        onReaction(event);
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent event) {
        onReaction(event);
    }

    private void onReaction(SingleReactionEvent event) {
        if (event.getReaction().map(Reaction::getEmoji).map(Emoji::asUnicodeEmoji).isEmpty()
                || event.getMessageAuthor().map(MessageAuthor::asUser).isEmpty()
                || event.getUser().filter(User::isBot).isPresent()) {
            return;
        }

        if (event.getUser().isEmpty()) {
            event.requestUser().thenAccept(user -> this.onReaction(event, user));
        } else {
            this.onReaction(event, event.getUser().get());
        }

    }

    private void onReaction(SingleReactionEvent event, User user) {
        if (event.getMessage().isEmpty()) {
            return;
        }
        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(event.getMessage().get());
        if (playerGame == null || !playerGame.canPlay(user) || event.getReaction().isEmpty()) {
            return;
        }

        Emoji em = event.getReaction().get().getEmoji();
        Player player = playerGame.getPlayer();

        Direction direction = Direction.fromEmoji(em);

        if (direction == null) {
            return;
        }

        player.move(direction);

        playerGame.update();
    }
}
