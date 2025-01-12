package io.github.universityTycoon;

/**
 * ADDED IN ASSESSMENT 2
 * Wrapper class used to store a name and satisfaction score so that they can be saved to the leaderboard map.
 * 
 * @param name the name of the user whose score is being saved.
 * @param score the score being saved.
 */
public class SavedScore implements Comparable<SavedScore> {
    private String name;
    private float score;

    /**
     * Constructor taking the following parameters.
     * @param name the name of the player.
     * @param score the player's score.
     */
    public SavedScore(String name, float score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's score.
     * @return the player's score.
     */
    public float getScore() {
        return score;
    }

    /**
     * Overrides the compareTo method from Comparable to allow for two instances of SavedScore to be directly compared -
     * in this case, the value being compared is the score attribute.
     */
    @Override
    public int compareTo(SavedScore otherScore) {
        return Float.compare(otherScore.getScore(), score);
    }
}
