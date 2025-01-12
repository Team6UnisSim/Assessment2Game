package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * BasketballCourt extends the LeisureBuilding class, and is one of the placeable building types.
 */
public class BasketballCourt extends LeisureBuilding  {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public BasketballCourt(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/fun_building.png");
        name = "Basketball Court";
        width = 3;
        height = 2;
        constructionGameTime = Duration.ofDays(100);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 30;
        studentRating = 3;
    }
}
