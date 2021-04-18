package fr.reminy.pokemon_discord.game;

import fr.reminy.pokemon_discord.Settings;
import fr.reminy.pokemon_discord.game.data.Collision;
import fr.reminy.pokemon_discord.game.data.Direction;
import fr.reminy.pokemon_discord.game.entity.Player;
import fr.reminy.pokemon_discord.game.http.GameHttpServer;
import fr.reminy.pokemon_discord.game.maps.PokemonMap;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.mapeditor.core.Map;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;

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



    public boolean isBlock(Collision collision) {

        if (collision == null)
            return false;

        int height = player.getHeight();

        switch (collision) {
            case TILE_1C -> {
                return height == 1;
            }
            case TILE_C1 ->  {
                return height == 2;
            }
            case TILE_1 -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public boolean move(Direction direction) {
        player.changeDirection(direction);

        int x = player.getX();
        int y = player.getY();

        switch (direction) {
            case LEFT -> x--;
            case RIGHT -> x++;
            case UP -> y--;
            case DOWN -> y++;
        }
        System.out.println(x + " " + y);

        Map map = currentMap.getMap();
        Tile tile = ((TileLayer)map.getLayer(3)).getTileAt(x,y);

        Collision collision = null;
        if (tile != null) {
            long id = tile.getId();
            collision = Collision.getCollisionById(id);
        }
        System.out.println(collision);

        boolean block = isBlock(collision);

        if (!block) {
            player.move(direction);
            changeHeight(collision);
        }
        return block;
    }

    private void changeHeight(Collision collision) {

        if (collision == null)
            return;

        switch (collision) {
            case TILE_12 -> player.changeHeight(2);
            case TILE_21 -> player.changeHeight(1);
        }
    }
}
