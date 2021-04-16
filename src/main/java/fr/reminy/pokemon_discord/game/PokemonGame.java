package fr.reminy.pokemon_discord.game;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.maps.PokemonMap;
import org.javacord.api.entity.message.Message;
import org.mapeditor.core.Map;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class PokemonGame {
    // Afficher map
    // -> serveur HTTP (quarkus) ? ou upload sur un hébergeur
    // Possibilité de se déplacer

    private final Player player;
    private PokemonMap currentMap;
    private final GameRenderer renderer;

    private Message linkedMessage = null;

    public PokemonGame(Player player, PokemonMap currentMap) {
        this.player = player;
        this.currentMap = currentMap;
        renderer = new GameRenderer(this, new Camera(player, Settings.CAMERA_WIDTH, Settings.CAMERA_HEIGHT));
    }

    public void update() {
        BufferedImage rendered = getRenderer().render();
        String playerImageURL = GameHttpServer.INSTANCE.setPlayerImage(linkedMessage.getAuthor().asUser().get().getId(), rendered);

        linkedMessage.edit(playerImageURL);
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

    public void setLinkedMessage(Message linkedMessage) {
        this.linkedMessage = linkedMessage;
    }

    public Optional<Message> getLinkedMessage() {
        return Optional.ofNullable(linkedMessage);
    }
}
