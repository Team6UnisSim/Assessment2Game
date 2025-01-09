package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class LargeAccommodation extends AccommodationBuilding {
    
    public LargeAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/large_sleep_building.png");
        name = "Large Accommodation";
        width = 4;
        height = 4;
        constructionGameTime = Duration.ofDays(80);
        buildingCapacity = 1000;
        finishDate = constructionStartedAt.plus(constructionGameTime);
        studentRating = 3;
    }
}
