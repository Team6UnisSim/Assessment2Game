package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

public class TeachingBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public TeachingBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }

    /**
     * Will calculate the satisfaction impact based off other aspects in the game.
     * @return the satisfaction impact.
     */
    @Override
    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {
        return 0f;
    }
}
