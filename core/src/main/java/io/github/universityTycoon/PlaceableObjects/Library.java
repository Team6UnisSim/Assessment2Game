package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class Library extends TeachingBuilding {

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public Library(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/library_building.png");
        width = 5;
        height = 4;
        constructionGameTime = Duration.ofDays(60);
        finishDate = constructionStartedAt.plus(constructionGameTime);
        buildingCapacity = 400;
        studentRating = 1;
    }
}
