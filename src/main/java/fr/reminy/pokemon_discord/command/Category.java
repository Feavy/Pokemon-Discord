package fr.reminy.pokemon_discord.command;

public enum Category {
    COMMON("Commandes usuelles"),
    GAME("Commandes de jeu");

    private final String label;

    Category(String label) {
        this.label = label;
    }
}
