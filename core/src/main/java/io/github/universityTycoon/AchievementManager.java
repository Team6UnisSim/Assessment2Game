package io.github.universityTycoon;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.universityTycoon.PlaceableObjects.Building;
import io.github.universityTycoon.PlaceableObjects.MapObject;

/**
 * ADDED IN ASSESSMENT 2
 * AchievementManager tracks the completion of all of the game's achievement types.
 * 
 * @param achievements an array of boolean values, each representing whether a given achievement type has been completed.
 * @param previousSatisfactionScore the overall satisfaction score when the method CheckContinousAchievements was last called.
 * @param timeReachedEightyPercent used to keep track of when a player's overall score is over 80%.
 */
public class AchievementManager {
    
    boolean[] achievements;
    float previousSatisfactionScore;
    float timeReachedEightyPercent;
    
    /**
     * Constructor for AchievementManager.
     */
    public AchievementManager() {
        achievements = new boolean[8];
        previousSatisfactionScore = 0;
    }

    /**
     * Checks that certain achievements have been completed once the game ends, updating their values in 
     * the array achievements.
     * 
     * @param satisfactionScore the current overall satisfaction score.
     * @param mapObjects the array of instances of MapObject that represents the game's map.
     * @param handledEvents a mapping of a GameEvent that has been dealt with by the player and its effect on the score.
     */
    public void checkGameEndAchievements(float satisfactionScore, MapObject[][] mapObjects, HashMap<GameEvent, Float> handledEvents) {
        if (satisfactionScore == 100) { // checks if Perfect Score has been completed.
            achievements[0] = true;
        } else if (satisfactionScore == 0) { // checks if Opposite Day has been completed.
            achievements[1] = true;
        }

        // Iterates through mapObjects to check building types and numbers that have been placed.
        int buildingNumber = 0;
        ArrayList<String> buildingTypes = new ArrayList<>();
        for (int i = 0; i < mapObjects.length; i++) {
            for (int j = 0; j < mapObjects[i].length; j++) {
                if (mapObjects[i][j] instanceof Building building) {
                    buildingNumber += 1;
                    if (!buildingTypes.contains(building.getName())) {
                        buildingTypes.add(building.getName());
                    }
                } 
            }
        }

        if (buildingNumber == 5) { // checks if Minimalist has been completed.
            achievements[3] = true;
        } else if (buildingNumber > 30) { // checks if Maximalist has been completed.
            achievements[4] = true;
        }
        if(buildingTypes.size() == 13) { // checks if Jack of All Trades has been completed.
            achievements[5] = true;
        }

        // Iterates through handledEvents to see what/how events have been dealt with by the user.
        int avertedNegativeEvents = 0;
        int mishandledNeutralEvents = 0;
        for (GameEvent i : handledEvents.keySet()) {
            if (i.getEventType() == EventTypes.FLOODING || i.getEventType() == EventTypes.HURRICANE || i.getEventType() == EventTypes.COFFEE_MACHINE_BREAKDOWN || i.getEventType() == EventTypes.STUDENT_PROTEST) {
                if (handledEvents.get(i) == 0) {
                    avertedNegativeEvents += 1;
                }
            } else if (i.getEventType() == EventTypes.GOOD_WEATHER || i.getEventType() == EventTypes.GEESE_INVASION) {
                if (handledEvents.get(i) < 0) {
                    mishandledNeutralEvents += 1;
                }
            }
        }
        if (avertedNegativeEvents >= 3) { // checks if Crisis Management has been completed.
            achievements[6] = true;
        }
        if (mishandledNeutralEvents >= 2) { // checks if Fool Me Twice has been completed.
            achievements[7] = true;
        }
    }

    /**
     * Keeps track of achievements that rely on things staying in effect over a certain duration.
     * 
     * @param satisfactionScore the current overall satisfaction score.
     * @param timeRemainingSeconds how long is left until the end of the game.
     */
    public void checkContinuousAchievements(float satisfactionScore, float timeRemainingSeconds) {
        // Checks if the score is over 80% - resets timeReachedEightyPercent if not.
        if (previousSatisfactionScore < 80 && satisfactionScore >= 80) {
            timeReachedEightyPercent = 300 - timeRemainingSeconds;
        }
        if(satisfactionScore < 80) {
            timeReachedEightyPercent = 301;
        }
        // Checks if I Heart Uni is completed - if the time between now and when 80% reached at least 3 minutes.
        if (((300 - timeRemainingSeconds) - timeReachedEightyPercent) >= 180 && timeReachedEightyPercent != 301) {
            achievements[2] = true;
        }
        previousSatisfactionScore = satisfactionScore; // updates previousSatisfactionScore every time the method is called.
    }

    /**
     * Calculates the updated satisfaction score based on the achievements completed.
     * 
     * @param satisfactionScore the current overall satisfaction score.
     * @return the updated satisfaction score reflecting achievement bonuses.
     */
    public float calculateNewSatisfactionScore(float satisfactionScore) {
        for (int i = 0; i < achievements.length; i++) {
            if (achievements[i] == true) {
                satisfactionScore += AchievementTypes.values()[i].scoreBonus; // adds the bonus for each type.
            }
        }
        // Keeps the new score within percentage range unless certain achievements completed.
        if(satisfactionScore > 100) {
            if(achievements[0] = true) {
                return 101f;
            } else {
                return 100f;
            }
        } else if (satisfactionScore == 0) {
            if(achievements[1] = true) {
                return -1f;
            } else {
                return 0;
            }
        } else {
            return satisfactionScore;
        }
    }

    /**
     * Returns the array of completion values for achievements.
     * @return the array of completion values for achievements
     */
    public boolean[] getAchievements() {
        return achievements;
    }
}
