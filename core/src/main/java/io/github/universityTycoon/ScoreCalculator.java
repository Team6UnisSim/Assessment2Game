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

    ArrayList<GameModifiers> activeModifiers = new ArrayList<GameModifiers>();
    int currentScore; 
    int currentStudents; // posssibly unneccessary

    // This will probably need a lot of parameters
    public int calculateScore(GameEvent event) {
        int scoreChange = 0;
        for (GameModifiers modifier : event.getModifiers()){
            scoreChange += modifier.getEffect();
        }
        return currentScore + scoreChange;
    }

    /**
     * Adds a modifier to the active list --> modify the score currently
     * @param modifier modifier to add
     */
    public void addActiveModifier(GameModifiers modifier){
        activeModifiers.add(modifier);
    }


    /**
     * Remove a modifier from the active list --> stop them from modifying the score
     * @param modifier modifier to remove
     */
    public void removeActiveModifier(GameModifiers modifier){
        activeModifiers.remove(modifier);
    }


    /**
     * Retrieve list of active modifiers 
     * @return list of active modifiers
     */
    public ArrayList<GameModifiers> getActiveModifiers(){
        return activeModifiers;
    
      ArrayList<GameModifiers> activeModifiers;

    public ScoreCalculator() {
        activeModifiers = new ArrayList<GameModifiers>();
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
            return 0;
        } else {
            return (campusScore + averageBuildingScore) / 2;
        }
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