package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * CHANGED IN ASSESSMENT 2 - LeisureBuilding is now an abstract class extended by other building types.
 * LeisureBuilding extends the Building class, and will be the superclass to different types of leisure building.
 */
public abstract class LeisureBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     * @param texturePath the file path for the image used to render this building.
     */
    public LeisureBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
