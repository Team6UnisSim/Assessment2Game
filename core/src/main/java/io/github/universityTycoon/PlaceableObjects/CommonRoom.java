package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * LeisureBuilding extends the Building class, and will be the superclass to different types of leisure building.
 *
 */
public class CommonRoom extends LeisureBuilding  {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public CommonRoom(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/fun_building.png");
        width = 2;
        height = 2;
        satisfactionBonus = 0;
        constructionGameTime = Duration.ofDays(45);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 150;
        studentRating = 4;
    }
}
