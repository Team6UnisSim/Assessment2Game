package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * StudentBar extends the LeisureBuilding class, and is one of the placeable building types.
 */
public class StudentBar extends LeisureBuilding  {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public StudentBar(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/fun_building.png");
        name = "Student Bar";
        width = 2;
        height = 4;
        constructionGameTime = Duration.ofDays(80);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 150;
        studentRating = 5;
    }
}
