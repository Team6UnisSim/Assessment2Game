package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * LeisureBuilding extends the Building class, and will be the superclass to different types of leisure building.
 *
 */
public class LeisureBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public LeisureBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
