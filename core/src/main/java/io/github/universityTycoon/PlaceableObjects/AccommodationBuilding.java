package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * AccommodationBuilding extends the Building class, and is the superclass to different types of accommodation
 * building.
 */
public class AccommodationBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public AccommodationBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
