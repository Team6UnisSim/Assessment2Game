package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class Cafe extends FoodAndDrink {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public Cafe(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/rec_building.png");
        width = 1;
        height = 1;
        satisfactionBonus = 2;
        constructionGameTime = Duration.ofDays(10);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 100;
        foodQuality = 7;
        hygieneRating = 1;
    }
}
