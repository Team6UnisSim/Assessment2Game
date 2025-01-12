package io.github.universityTycoon.PlaceableObjects;

import io.github.universityTycoon.TerrainTypes;

import java.time.LocalDateTime;

/**
 * ADDED IN ASSESSMENT 2
 * Terrain extends the abstract class MapObject, and is the superclass of all other terrain types.
 * @param satisfactionBonus the bonus added to the scores of nearby instances of Building. 
 */
public class Terrain extends MapObject {

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
     * Retrieves the terrain name.
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
     * Gets the width of the terrain.
     * @return The width of the terrain
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the terrain.
     * @return The height of the terrain
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the satisfaction bonus of the terrain.
     * @return the satisfaction bonus of the terrain.
     */
    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

    /**
     * Creates and returns a new instance of a specific terrain type based on the provided enum type.
     * The type of terrain returned corresponds to the TerrainTypes enum value.
     *
     * @param type The type of terrain to create, specified as a TerrainTypes enum value.
     * @param time The time the terrain will be constructed at.
     *
     * @return A new instance of the specified terrain type, constructed at the specified time.
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
