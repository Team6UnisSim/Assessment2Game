package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class MediumAccommodation extends AccommodationBuilding {
    
    public MediumAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/sleep_building.png");
        width = 3;
        height = 3;
        constructionGameTime = Duration.ofDays(50);
        satisfactionBonus = 1;
        rentPricePPM = 700;
        buildingCapacity = 500;
        finishDate = constructionStartedAt.plus(constructionGameTime);
    }
}
