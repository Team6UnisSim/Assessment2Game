package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * CHANGED IN ASSESSMENT 2 - AccommodationBuilding is now an abstract class extended by other building types.
 * AccommodationBuilding extends the Building class, and is the superclass to different types of accommodation
 * building.
 */
public abstract class AccommodationBuilding extends Building {

    /** 
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     * @param texturePath the file path for the image used to render this building.
     */
    public AccommodationBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
