package io.github.universityTycoon.PlaceableObjects;

/**
 * CHANGED IN ASSESSMENT 2 - size attribute/getters replaced with width and height.
 * An abstract class representing anything that can be placed on a map.
 */
public abstract class MapObject {
    String name = ""; 
    String texturePath = ""; // Relative path to the texture, e.g. "images/rec_building.png"
    boolean isStackable = false; // Whether MapObjects can be place on top of this MapObject
    int width = 1; // ADDED IN ASSESSMENT 2 - How many squares it takes up in the x axis.
    int height = 1; // ADDED IN ASSESSMENT 2 - How many squares it takes up in the y axis.
    float satisfactionBonus = 0f; // ADDED IN ASSESSMENT 2 - the value added to the individual scores of nearby buildings.

    public abstract float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects); // CHANGED IN ASSESSMENT 2 - all parameters added.

    public abstract String getName();
    public abstract String getTexturePath();
    public abstract boolean getIsStackable();
    public abstract int getWidth(); // ADDED IN ASSESSMENT 2
    public abstract int getHeight(); // ADDED IN ASSESSMENT 2
    public abstract float getSatisfactionBonus(); // ADDED IN ASSESMENT 2
}
