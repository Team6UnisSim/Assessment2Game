package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * StemBuilding extends the TeachingBuilding class, and is one of the placeable building types.
 */
public class StemBuilding extends TeachingBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public StemBuilding(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/library_building.png");
        name = "STEM Building";
        width = 2;
        height = 2;
        constructionGameTime = Duration.ofDays(180);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 400;
        studentRating = 2;
    }
}
