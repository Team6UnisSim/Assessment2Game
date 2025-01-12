package io.github.universityTycoon;

// ADDED IN ASSESSMENT 2
public enum AchievementTypes {
    perfectScore("Perfect Score", "End the game with 100% student satisfaction.", 1f),
    zeroScore("Opposite Day", "End the game with 0% student satisfaction.", -1f),
    consistent("I Heart Uni", "Maintain over 80% student satisfaction for 3 straight minutes.", 5f),
    minimalist("Minimalist", "Place only 5 buildings during a game.", 0),
    maximalist("Maximalist", "Place over 30 buildings during a game.", 0),
    variation("Jack of All Trades", "Place exactly one of every building during a game.", 10f),
    crisisManagement("Crisis Management", "Successfully avert 3 negative events during a game.", 5f),
    foolMeTwice("Fool Me Twice", "Cause negative consequences when dealing with 2 neutral events during a game.", -5f);

    public final String name;
    public final String description;
    public final float scoreBonus;

    private AchievementTypes(String name, String description, float scoreBonus) {
        this.name = name;
        this.description = description;
        this.scoreBonus = scoreBonus;
    }
}
