package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class MediumAccommodation extends AccommodationBuilding {
    
    public MediumAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/medium_sleep_building.png");
        name = "Medium Accommodation";
        width = 3;
        height = 3;
        constructionGameTime = Duration.ofDays(50);
        buildingCapacity = 500;
        finishDate = constructionStartedAt.plus(constructionGameTime);
        studentRating = 4;
    }
}
