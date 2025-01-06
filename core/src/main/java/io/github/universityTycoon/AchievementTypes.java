package io.github.universityTycoon;

public enum AchievementTypes {
    perfectScore("Perfect Score", "End the game with 100% student satisfaction."),
    zeroScore("Opposite Day", "End the game with 0% student satisfaction."),
    consistent("I Heart Uni", "Maintain over 80% student satisfaction for 3 straight minutes."),
    minimalist("Minimalist", "Place only 5 buildings during a game."),
    maximalist("Maximalist", "Place 30 buildings during a game.");

    public final String name;
    public final String description;

    private AchievementTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
