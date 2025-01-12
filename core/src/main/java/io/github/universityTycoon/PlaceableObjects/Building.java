package io.github.universityTycoon.PlaceableObjects;

import com.badlogic.gdx.math.MathUtils;
import io.github.universityTycoon.BuildingTypes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * CHANGED IN ASSESSMENT 2 - all static variables made instance variables, height/width and student rating added, size removed.
 * Building extends the abstract class MapObject, and is the superclass of all other building types.
 *
 * @param constructionGameTime The duration it will take for the building to be built.
 * @param finishDate The in game date the building will finish being built.
 * @param isUnderConstruction A property that states whether the building is under construction.
 * @param buildingCapacity How many people can live in a building / how many people it can hold.
 * @param studentRating How much students like the building - used to calculate building satisfaction scores.
 */
public class Building extends MapObject {

    public Duration constructionGameTime; // CHANGED IN ASSESSMENT 2 - changed from a static value.
    public LocalDateTime finishDate;
    public boolean isUnderConstruction = true;
    public float satisfactionBonus; // CHANGED IN ASSESSMENT 2 - changed from a static value.
    public float buildingCapacity; // CHANGED IN ASSESSMENT 2 - changed from a static value.
    public int studentRating; // ADDED IN ASSESSMENT 2

    LocalDateTime constructionStartedAt; // In-game time.

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts
     * @param texturePath The file path for the texture.
     */
    public Building(LocalDateTime constructionStartedAt, String texturePath) {
        this.constructionStartedAt = constructionStartedAt;
        this.texturePath = texturePath;
        satisfactionBonus = 0;
    }

    /**
     * Constructor with the following parameters.
     *
     * @param constructionStartedAt The date the construction starts
     * @param texturePath The file path for the texture.
     * @param constructionGameTime The time it will take to construct the building.
     */
    public Building(LocalDateTime constructionStartedAt, String texturePath, Duration constructionGameTime) {
        this.constructionStartedAt = constructionStartedAt;
        this.constructionGameTime = constructionGameTime;
        this.texturePath = texturePath;
        finishDate = constructionStartedAt.plus(constructionGameTime); // CHANGED IN ASSESSMENT 2 - moved from individual building types.
        satisfactionBonus = 0;
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
     * CHANGED IN ASSESSMENT 2 - changed from a static method.
     * Retrieves the satisfaction bonus provided by this building.
     *
     * @return The bonus.
     */
    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

    /**
     * CHANGED IN ASSESSMENT 2 - changed from a static method.
     * Retrieves the capacity of the building.
     *
     * @return The capacity.
     */
    public float getBuildingCapacity() {
        return buildingCapacity;
    }

    /**
     * // CHANGED IN ASSESSMENT 2 - changed from a static method.
     * Retrieves the time taken to construct the building.
     *
     * @return The time taken to construct the building.
     */
    public Duration getConstructionGameTime() {
        return constructionGameTime;
    }


    /**
     * Returns if where the object is stackable.
     *
     * @return true if the object is stackable, false otherwise.
     */
    public boolean getIsStackable() {
        return isStackable;
    }

    /**
     * CHANGED IN ASSESSMENT 2 - not implemented in Assessment 1.
     * Calculates the satisfaction score for this instance of Building based on the nearby instances of MapObjects,
     * by adding them to an array, iterating through and checking if their cumulative scores (capacity multiplied by rating)
     * meet a minimum score based on the capacity of this instance.
     * 
     * @param ownX the X coordinate of this instance of Building.
     * @param ownY the Y coordinate of this instance of Building.
     * @param mapObjects the array of MapObject that represents the game's map.
     * @return the satisfaction score (as a percentage) for this instance.
     */
    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {

        ArrayList<MapObject> nearbyObjects = new ArrayList<>(); // all nearby objects are added to this.
        float satisfaction = 0;

        // Checks for nearby objects up and right - needed splitting to avoid going out of bounds.
        for (int i = ownX; i < ownX + width + 6 && i < mapObjects.length; i++) {
            for (int j = ownY; j < ownY + height + 6 && j < mapObjects[i].length; j++) {
                if(mapObjects[i][j] != null && mapObjects[i][j] != this) {
                    if (mapObjects[i][j] instanceof MapObjectPointer pointer) { // checks for buildings out of bounds with pointers nearby
                        if(!nearbyObjects.contains(pointer.original) && pointer.original != this) {
                            nearbyObjects.add(pointer.original);
                        }
                    } else {
                        if(!nearbyObjects.contains(mapObjects[i][j])) {
                            nearbyObjects.add(mapObjects[i][j]);
                        }
                    }
                }
            }
        }

        // Checks for nearby objects down and left.
        for (int i = ownX; i > ownX - 6 && i >= 0; i--) {
            for (int j = ownY; j > ownY - 6 && j >= 0; j--) {
                if(mapObjects[i][j] != null && mapObjects[i][j] != this) {
                    if (mapObjects[i][j] instanceof MapObjectPointer pointer) {
                        if(!nearbyObjects.contains(pointer.original) && pointer.original != this) {
                            nearbyObjects.add(pointer.original);
                        }
                    } else {
                        if(!nearbyObjects.contains(mapObjects[i][j])) {
                            nearbyObjects.add(mapObjects[i][j]);
                        }
                    }
                }
            }
        }

        float minimumNearbyScore = 9 * buildingCapacity; // since there are 3 building types (assumes average student rating of 3).
        float nearbyScore = 0f;
        // Iterates through and finds the combined score of all nearby buildings.
        for(MapObject i : nearbyObjects) {
            if (i instanceof Building building) {
                if(!building.isUnderConstruction) {
                    nearbyScore += building.getBuildingCapacity() * building.getStudentRating();
                } else {
                    satisfaction -= 10;
                }
            } else {
                satisfaction += i.getSatisfactionBonus(); // Adds the bonus for nearby non-buildings (e.g. Terrain).
            }
        }

        // Updates satisfaction with how close the nearby score is to the minimum.
        if(nearbyScore >= minimumNearbyScore) {
            satisfaction += 100;
        } else {
            satisfaction += 100 * (nearbyScore / minimumNearbyScore);
        }

        // Always returns a valid percentage.
        if(satisfaction > 100) {
            return 100f;
        } else {
            return satisfaction;
        }
    }

    /**
     * Updates isUnderConstruction when the building is finished being built.
     * @param currentGameTime the in game time
     */
    public void update(LocalDateTime currentGameTime) {
        if (currentGameTime.isAfter(finishDate)) {
            isUnderConstruction = false;
        }
    }

    /**
     * Returns the current construction progress as a percentage.
     * @param currentGameTime The current in game time.
     * @return The construction progress as a percentage.
     */
    public float getConstructionPercent(LocalDateTime currentGameTime) {
        Duration timePassed = Duration.between(constructionStartedAt, currentGameTime);
        float percent = MathUtils.clamp((float)timePassed.getSeconds() / (float)constructionGameTime.getSeconds(), 0, 1);
        return percent * 100;
    }

    /**
     * ADDED IN ASSESSMENT 2
     * Gets the width of the building.
     * @return The width of the building
     */
    public int getWidth() {
        return width;
    }

    /**
     * ADDED IN ASSESSMENT 2
     * Gets the height of the building.
     * @return The height of the building
     */
    public int getHeight() {
        return height;
    }

    /**
     * ADDED IN ASSESSMENT 2
     * Gets the student rating of the building.
     * @return The student rating of the building
     */
    public int getStudentRating() {
        return studentRating;
    }


    /**
     * CHANGED IN ASSESSMENT 2 - more building types added.
     * Creates and returns a new instance of a specific building type based on the provided enum type.
     * The type of building returned corresponds to the 'BuildingTypes' enum value.
     *
     * @param type The type of building to create, specified as a 'BuildingTypes' enum value.
     * @param time The time the building will be constructed at.
     *
     * @return A new instance of the specified building type, constructed at the specified time.
     */
    public static Building getObjectFromEnum(BuildingTypes type, LocalDateTime time) {
        return switch (type) {
            case SmallAccommodation -> new SmallAccommodation(time);
            case MediumAccommodation -> new MediumAccommodation(time);
            case LargeAccommodation -> new LargeAccommodation(time);
            case DiningHall -> new DiningHall(time);
            case ConvenienceStore -> new ConvenienceStore(time);
            case Cafe -> new Cafe(time);
            case CommonRoom -> new CommonRoom(time);
            case StudentBar -> new StudentBar(time);
            case BasketballCourt -> new BasketballCourt(time);
            case StemBuilding -> new StemBuilding(time);
            case HumanitiesBuilding -> new HumanitiesBuilding(time);
            case ArtsBuilding -> new ArtsBuilding(time);
            case Library -> new Library(time);
            default -> null;
        };
    }
}
