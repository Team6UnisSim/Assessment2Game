package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * MediumAccommodation extends the AccommodationBuilding class, and is one of the placeable building types.
 */
public class MediumAccommodation extends AccommodationBuilding {
    
    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts at.
     */
    public MediumAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/medium_sleep_building.png");
        name = "Medium Accommodation";
        width = 3;
        height = 3;
        constructionGameTime = Duration.ofDays(200);
        buildingCapacity = 500;
        finishDate = constructionStartedAt.plus(constructionGameTime);
        studentRating = 4;
    }
}
