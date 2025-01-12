package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * DiningHall extends the FoodAndDrinkBuilding class, and is one of the placeable building types.
 */
public class DiningHall extends FoodAndDrinkBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public DiningHall(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/rec_building.png");
        name = "Dining Hall";
        width = 4;
        height = 3;
        constructionGameTime = Duration.ofDays(200);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 200;
        studentRating = 2;
    }
}
