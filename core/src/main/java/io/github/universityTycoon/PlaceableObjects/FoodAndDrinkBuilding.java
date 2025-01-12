package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * CHANGED IN ASSESSMENT 2 - FoodAndDrinkBuilding (renamed from Cafeteria) is now an abstract class 
 * extended by other building types.
 * FoodAndDrinkBuilding extends the Building class, and is the superclass to different types of food and drink
 * building.
 */
public abstract class FoodAndDrinkBuilding extends Building {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     * @param texturePath the file path for the image used to render this building.
     */
    public FoodAndDrinkBuilding(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
    }
}
