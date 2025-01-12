package io.github.universityTycoon;

public class SavedScore implements Comparable<SavedScore> {
    private String name;
    private float score;

    public SavedScore(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }

    @Override
    public int compareTo(SavedScore otherScore) {
        return Float.compare(otherScore.getScore(), score);
    }
}
