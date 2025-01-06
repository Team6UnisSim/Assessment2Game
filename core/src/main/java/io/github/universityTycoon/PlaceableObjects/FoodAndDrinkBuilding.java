package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

public class FoodAndDrinkBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public FoodAndDrinkBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
