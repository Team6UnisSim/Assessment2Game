package io.github.universityTycoon;

// CHANGED IN ASSESSMENT 2 - more building types added, category added to enum.
public enum BuildingTypes {
    SmallAccommodation("Accommodation"),
    MediumAccommodation("Accommodation"), 
    LargeAccommodation("Accommodation"), 
    DiningHall("Food & Drink"),
    Cafe("Food & Drink"),
    ConvenienceStore("Food & Drink"),
    CommonRoom("Leisure"),
    StudentBar("Leisure"),
    BasketballCourt("Leisure"),
    StemBuilding( "Teaching"),
    HumanitiesBuilding("Teaching"),
    ArtsBuilding("Teaching"),
    Library("Teaching");
    
    public final String category; // ADDED IN ASSESSMENT 2

    private BuildingTypes(String category) {
        this.category = category;
    }
}
