package io.github.universityTycoon.PlaceableObjects;

import io.github.universityTycoon.TerrainTypes;

import java.time.LocalDateTime;

/**
 * Terrain extends the abstract class MapObject, and is the superclass of all other building types.
 */
public class Terrain extends MapObject {

    // All of these building statistics (such as buildingCapacity), and those for its subclasses are currently static,
    // as we are not currently making subclasses of accommodation buildings for example, when that is the case,
    // those classes will have their own values, and at that point, these statistics should be made no longer static.
    public float satisfactionBonus;

    /**
     * Constructor with the following parameters.
     *
     * @param texturePath The file path for the texture.
     */
    public Terrain(String texturePath) {
        this.texturePath = texturePath;
    }

    /**
     * Retrieves the building name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the file path of the texture associated with this object.
     * @return File path as a string.
     */
    public String getTexturePath() {
        return texturePath;
    }

    /**
     * Returns if where the object is stackable.
     *
     * @return true if the object is stackable, false otherwise.
     */
    public boolean getIsStackable() {
        return isStackable;
    }

    // As a percentage (not implemented)
    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {
        return 1f;
    }

    /**
     * Gets the width of the building.
     * @return The width of the building
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the building.
     * @return The height of the building
     */
    public int getHeight() {
        return height;
    }

    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

    /**
     * Creates and returns a new instance of a specific building type based on the provided enum type.
     * The type of building returned corresponds to the `BuildingTypes` enum value.
     *
     * @param type The type of building to create, specified as a `BuildingTypes` enum value.
     * @param time The time the building will be constructed at.
     * @param <T> A generic type parameter that extends the `Building` class, representing the type of building to create.
     *
     * @return A new instance of the specified building type, constructed at the specified time.
     */
    public static Terrain getObjectFromEnum(TerrainTypes type, LocalDateTime time) {
        return switch (type) {
            case Road -> new Road();
            case Water -> new Water();
            case Tree -> new Tree();
            case LargeTrees -> new LargeTrees();
            default -> null;
        };
    }
}
