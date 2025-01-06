package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * LeisureBuilding extends the Building class, and will be the superclass to different types of leisure building.
 *
 */
public class StudentBar extends LeisureBuilding  {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public StudentBar(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/fun_building.png");
        width = 2;
        height = 4;
        constructionGameTime = Duration.ofDays(45);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 150;
        studentRating = 5;
    }
}
