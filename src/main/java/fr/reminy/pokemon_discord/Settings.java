package fr.reminy.pokemon_discord;

public class Settings {
    public final static String PREFIX = "a!";
    public static final int CAMERA_WIDTH = 15;
    public static final int CAMERA_HEIGHT = 10;
    public static final int SCALE_FACTOR = 2;
    public static final int ID_COLLISIONS = 3;
    public static final int ID_EVENTS = 4;
    public static final String HOST = System.getenv("HOST");
    public static final int PORT = 8081;
    public static final boolean IS_PRODUCTION = HOST != null;
}
