package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * SmallAccommodation extends the AccommodationBuilding class, and is one of the placeable building types.
 */
public class SmallAccommodation extends AccommodationBuilding {
    
    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public SmallAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/sleep_building.png");
        name = "Small Accommodation";
        width = 2;
        height = 2;
        constructionGameTime = Duration.ofDays(60);
        buildingCapacity = 300;
        finishDate = constructionStartedAt.plus(constructionGameTime);
        studentRating = 4;
    }
}
