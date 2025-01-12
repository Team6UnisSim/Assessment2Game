package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * LargeAccommodation extends the AccommodationBuilding class, and is one of the placeable building types.
 */
public class LargeAccommodation extends AccommodationBuilding {
    
    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public LargeAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/large_sleep_building.png");
        name = "Large Accommodation";
        width = 4;
        height = 4;
        constructionGameTime = Duration.ofDays(365);
        buildingCapacity = 1000;
        finishDate = constructionStartedAt.plus(constructionGameTime);
        studentRating = 3;
    }
}
