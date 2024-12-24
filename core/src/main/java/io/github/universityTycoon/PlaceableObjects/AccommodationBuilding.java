package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * AccommodationBuilding extends the Building class, and is the superclass to different types of accommodation
 * building.
 *
 * @param rentPricePPM The rent per month
 *
 */
public class AccommodationBuilding extends Building {
    static int rentPricePPM;

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public AccommodationBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }

    /**
     * Retrieves the rent price per month.
     * @return The rent per month.
     */
    public static int getRentPricePPM() {
        return rentPricePPM;
    }


    /**
     * Will calculate the satisfaction impact based off other aspects in the game.
     * @return the satisfaction impact.
     */
    @Override
    public float calculateSatisfaction() {
        return 0f;
    }
}
