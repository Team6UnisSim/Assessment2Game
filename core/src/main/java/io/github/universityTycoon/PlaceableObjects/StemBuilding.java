package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class StemBuilding extends TeachingBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public StemBuilding(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/library_building.png");
        width = 2;
        height = 2;
        constructionGameTime = Duration.ofDays(60);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 400;
        studentRating = 2;
    }
}
