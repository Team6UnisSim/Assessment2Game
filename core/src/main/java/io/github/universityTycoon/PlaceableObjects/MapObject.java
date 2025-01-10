package io.github.universityTycoon.PlaceableObjects;

/**
 * An abstract class representing anything that can be placed on a map
 */
public abstract class MapObject {
    String name = "";
    String texturePath = ""; // Relative path to the texture, e.g. "images/rec_building.png"
    boolean isStackable = false; // Whether MapObjects can be place on top of this MapObject
    int width = 1; // How many squares it takes up in the x axis.
    int height = 1; // How many squares it takes up in the y axis.
    float satisfactionBonus = 0f;

    public abstract float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects); // As a percentage

    public abstract String getName();
    public abstract String getTexturePath();
    public abstract boolean getIsStackable();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract float getSatisfactionBonus();
}
