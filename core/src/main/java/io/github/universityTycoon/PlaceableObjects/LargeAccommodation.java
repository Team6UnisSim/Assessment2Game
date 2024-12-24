package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class LargeAccommodation extends AccommodationBuilding {
    
    public LargeAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/sleep_building.png");
        width = 4;
        height = 4;
        constructionGameTime = Duration.ofDays(80);
        satisfactionBonus = 1;
        rentPricePPM = 700;
        buildingCapacity = 700;
        finishDate = constructionStartedAt.plus(constructionGameTime);
    }
}
