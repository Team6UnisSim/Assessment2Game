package io.github.universityTycoon.PlaceableObjects;

import java.time.Duration;
import java.time.LocalDateTime;

public class SmallAccommodation extends AccommodationBuilding {
    
    public SmallAccommodation(LocalDateTime constructionStartedAt) {
        super(constructionStartedAt, "images/sleep_building.png");
        width = 2;
        height = 2;
        constructionGameTime = Duration.ofDays(30);
        satisfactionBonus = 1;
        rentPricePPM = 700;
        buildingCapacity = 300;
        finishDate = constructionStartedAt.plus(constructionGameTime);
    }
}
