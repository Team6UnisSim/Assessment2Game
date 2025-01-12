package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class Cafe extends FoodAndDrinkBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public Cafe(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/rec_building.png");
        name = "Cafe";
        width = 1;
        height = 1;
        constructionGameTime = Duration.ofDays(60);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 30;
        studentRating = 4;
    }
}
