package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * LeisureBuilding extends the Building class, and will be the superclass to different types of leisure building.
 *
 */
public class BasketballCourt extends LeisureBuilding  {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public BasketballCourt(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/fun_building.png");
        width = 3;
        height = 2;
        constructionGameTime = Duration.ofDays(60);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 30;
        studentRating = 3;
    }
}
