package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class ConvenienceStore extends FoodAndDrinkBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public ConvenienceStore(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/rec_building.png");
        width = 2;
        height = 2;
        constructionGameTime = Duration.ofDays(10);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 100;
        studentRating = 3;
    }
}
