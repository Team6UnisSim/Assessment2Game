package io.github.universityTycoon;

public class SavedScore {
    private String name;
    private Float score;

    public SavedScore(String name, Float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Float getScore() {
        return score;
    }
}
