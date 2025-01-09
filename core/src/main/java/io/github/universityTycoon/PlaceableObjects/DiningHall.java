package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

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
        constructionGameTime = Duration.ofDays(10);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 100;
        studentRating = 2;
    }
}
