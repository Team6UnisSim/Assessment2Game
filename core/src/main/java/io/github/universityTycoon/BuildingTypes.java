package io.github.universityTycoon;

public enum BuildingTypes {
    SmallAccommodation("Small Accommodation", "Accommodation"),
    MediumAccommodation("Medium Accommodation", "Accommodation"), 
    LargeAccommodation("Large Accommodation", "Accommodation"), 
    DiningHall("Dining Hall", "Food & Drink"),
    Cafe("Cafe", "Food & Drink"),
    ConvenienceStore("Convenience Store", "Food & Drink"),
    CommonRoom("Common Room", "Leisure"),
    StudentBar("Student Bar", "Leisure"),
    BasketballCourt("Basketball Court", "Leisure"),
    StemBuilding("STEM Building", "Teaching"),
    HumanitiesBuilding("Humanities Building", "Teaching"),
    ArtsBuilding("Arts Building", "Teaching"),
    Library("Library", "Teaching");
    
    public final String name;
    public final String category;

    private BuildingTypes(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
