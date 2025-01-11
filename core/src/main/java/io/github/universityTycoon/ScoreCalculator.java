package io.github.universityTycoon;

import java.util.ArrayList;

import io.github.universityTycoon.PlaceableObjects.AccommodationBuilding;
import io.github.universityTycoon.PlaceableObjects.Building;
import io.github.universityTycoon.PlaceableObjects.FoodAndDrinkBuilding;
import io.github.universityTycoon.PlaceableObjects.LeisureBuilding;
import io.github.universityTycoon.PlaceableObjects.MapObject;
import io.github.universityTycoon.PlaceableObjects.TeachingBuilding;

/**
 * Class to invoke to calculate the current satisfaction score. Stores a list of active modifiers that can affect how
 * satisfaction is calculated.
 */
public class ScoreCalculator {

    ArrayList<Float> activeModifiers = new ArrayList<>();
    float currentScore; 
    int currentStudents; // posssibly unneccessary

    /**
     * Adds a modifier to the active list --> modify the score currently
     * @param modifier modifier to add
     */
    public void addActiveModifier(Float modifier){
        activeModifiers.add(modifier);
    }


    /**
     * Remove a modifier from the active list --> stop them from modifying the score
     * @param modifier modifier to remove
     */
    public void removeActiveModifier(Float modifier){
        activeModifiers.remove(modifier);
    }


    /**
     * Retrieve list of active modifiers 
     * @return list of active modifiers
     */
    public ArrayList<Float> getActiveModifiers() {
        return activeModifiers;
    }

    public float calculateScore(MapObject[][] mapObjects) {
        float campusScore = calculateCampusScore(mapObjects);
        float averageBuildingScore = 0;
        for (int i = 0; i < mapObjects.length; i++) {
            for (int j = 0; j < mapObjects[i].length; j++) {
                if(mapObjects[i][j] instanceof Building building && !building.isUnderConstruction) {
                    if(averageBuildingScore == 0) {
                        averageBuildingScore += building.calculateSatisfaction(i, j, mapObjects);
                    } else {
                        averageBuildingScore = (averageBuildingScore + building.calculateSatisfaction(i, j, mapObjects)) / 2;
                    }
                }
            }
        }

        if(campusScore == 0) {
            currentScore = 0;
        } else {
            currentScore = (campusScore + averageBuildingScore) / 2;
        }
        for (Float i : activeModifiers) {
            currentScore += i;
        }
        return Math.max(0, Math.min(100, currentScore));
    }

    /**
     * Calculates whether the entire campus has the adequate resources to cater for its students.
     * @param mapObjects 
     * @return the satisfaction score as a percentage.
     */
    private float calculateCampusScore(MapObject[][] mapObjects) {
        float currentStudents = 0;
        float[] buildingTypeCapacities = new float[3];

        for (int i = 0; i < mapObjects.length; i++) {
            for (int j = 0; j < mapObjects[i].length; j++) {
                if (mapObjects[i][j] instanceof Building building) {
                    if (!building.isUnderConstruction) { 
                        if (building instanceof AccommodationBuilding) {
                            currentStudents += building.getBuildingCapacity();
                        } else if (building instanceof TeachingBuilding) {
                            buildingTypeCapacities[0] += building.getBuildingCapacity();
                        } else if (building instanceof FoodAndDrinkBuilding) {
                            buildingTypeCapacities[1] += building.getBuildingCapacity();
                        } else if (building instanceof LeisureBuilding) {
                            buildingTypeCapacities[2] += building.getBuildingCapacity();
                        }
                    }
                }
            }
        }
        
        if(currentStudents == 0) {
            return 0;
        } else {
            float output = 0;
            for(float i : buildingTypeCapacities) {
                if(i != 0) {
                    if(i >= currentStudents) {
                        output += 100;
                    } else {
                        output += (100 * (i / currentStudents));
                    }
                }
            }
            return output / 3;
        }
    }
}