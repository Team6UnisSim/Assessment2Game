package io.github.universityTycoon.PlaceableObjects;

import com.badlogic.gdx.math.MathUtils;
import io.github.universityTycoon.BuildingTypes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Building extends the abstract class MapObject, and is the superclass of all other building types.
 *
 * @param constructionGameTime The duration it will take for the building to be built.
 * @param finishDate The in game date the building will finish being built.
 * @param isUnderConstruction A property that states whether the building is under construction.
 */
public class Building extends MapObject {

    public Duration constructionGameTime;
    public LocalDateTime finishDate;
    public boolean isUnderConstruction = true;

    // All of these building statistics (such as buildingCapacity), and those for its subclasses are currently static,
    // as we are not currently making subclasses of accommodation buildings for example, when that is the case,
    // those classes will have their own values, and at that point, these statistics should be made no longer static.
    public float satisfactionBonus;

    // This is multipurpose, it's how many people can live in an accommodation building, how many can eat in the cafeteria
    // at any given time, how many can leisure? in a leisure building, and how many can be taught at once in a teaching building.
    // These should affect the satisfaction later on, meaning if 2000 students attend the university, the other buildings
    // should have a capacity that is high enough to accommodate for that, or the satisfaction score reduces.
    public float buildingCapacity;

    LocalDateTime constructionStartedAt; // IN-GAME TIME

    int studentRating;

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
        finishDate = constructionStartedAt.plus(constructionGameTime);
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
     * Retrieves the satisfaction bonus provided by this building.
     *
     * @return The bonus.
     */
    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

    /**
     * Retrieves the capacity of the building.
     *
     * @return The capacity.
     */
    public float getBuildingCapacity() {
        return buildingCapacity;
    }

    /**
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

    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) {
        ArrayList<MapObject> nearbyObjects = new ArrayList<>();
        float satisfaction = 0;

        // Checks for nearby objects up and right - needed splitting to avoid going out of bounds.
        for (int i = ownX; i < ownX + width + 6 && i < mapObjects.length; i++) {
            for (int j = ownY; j < ownY + height + 6 && j < mapObjects[i].length; j++) {
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

        float minimumNearbyScore = 6 * buildingCapacity;
        float nearbyScore = 0f;
        for(MapObject i : nearbyObjects) {
            if (i instanceof Building building) {
                if(!building.isUnderConstruction) {
                    nearbyScore += building.getBuildingCapacity() * building.getStudentRating();
                } else {
                    satisfaction -= 10;
                }
            } else {
                satisfaction += i.getSatisfactionBonus();
            }
        }

        if(nearbyScore >= minimumNearbyScore) {
            satisfaction += 100;
        } else {
            satisfaction += 100 * (nearbyScore / minimumNearbyScore);
        }

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

    public int getStudentRating() {
        return studentRating;
    }


    /**
     * Creates and returns a new instance of a specific building type based on the provided enum type.
     * The type of building returned corresponds to the 'BuildingTypes' enum value.
     *
     * @param type The type of building to create, specified as a 'BuildingTypes' enum value.
     * @param time The time the building will be constructed at.
     * @param <T> A generic type parameter that extends the 'Building' class, representing the type of building to create.
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
