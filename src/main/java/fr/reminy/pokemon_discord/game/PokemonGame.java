package fr.reminy.pokemon_discord.game;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.game.maps.PokemonMap;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class PokemonGame {
    private final Player player;
    private final GameRenderer renderer;
    private final User user;
    private PokemonMap currentMap;
    private Message linkedMessage = null;

    public PokemonGame(User user, Player player, PokemonMap currentMap) {
        this.user = user;
        this.player = player;
        this.currentMap = currentMap;
        renderer = new GameRenderer(this, new Camera(player, Settings.CAMERA_WIDTH, Settings.CAMERA_HEIGHT));
    }

    public void update() {
        BufferedImage rendered = getRenderer().render();
        String playerImageURL = GameHttpServer.INSTANCE.setPlayerImage(user.getId(), rendered);
        linkedMessage.edit(playerImageURL);
    }

    public boolean canPlay(User user) {
        return this.user.getId() == user.getId();
    }

    public Player getPlayer() {
        return player;
    }

    public PokemonMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(PokemonMap currentMap) {
        this.currentMap = currentMap;
    }

    public GameRenderer getRenderer() {
        return renderer;
    }

    public Optional<Message> getLinkedMessage() {
        return Optional.ofNullable(linkedMessage);
    }

    public void setLinkedMessage(Message linkedMessage) {
        this.linkedMessage = linkedMessage;
    }
}
