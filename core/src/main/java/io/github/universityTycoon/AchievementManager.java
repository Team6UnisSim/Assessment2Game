package io.github.universityTycoon;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.universityTycoon.PlaceableObjects.Building;
import io.github.universityTycoon.PlaceableObjects.MapObject;

/**
 * AchievementManager tracks the completion of all of the game's achievement types.
 */
public class AchievementManager {
    boolean[] achievements;
    float previousSatisfactionScore;
    float timeReachedEightyPercent;
    
    public AchievementManager() {
        achievements = new boolean[8];
        previousSatisfactionScore = 0;
    }

    public void checkGameEndAchievements(float satisfactionScore, MapObject[][] mapObjects, HashMap<GameEvent, Float> handledEvents) {
        if (satisfactionScore == 100) {
            achievements[0] = true;
        } else if (satisfactionScore == 0) {
            achievements[1] = true;
        }

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

        if (buildingNumber == 5) {
            achievements[3] = true;
        } else if (buildingNumber > 30) {
            achievements[4] = true;
        }
        if(buildingTypes.size() == 13) {
            achievements[5] = true;
        }

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
        if (avertedNegativeEvents >= 3) {
            achievements[6] = true;
        }
        if (mishandledNeutralEvents >= 2) {
            achievements[7] = true;
        }
    }

    public void checkContinuousAchievements(float satisfactionScore, float timeRemainingSeconds) {
        if (previousSatisfactionScore < 80 && satisfactionScore >= 80) {
            timeReachedEightyPercent = 300 - timeRemainingSeconds;
        }
        if(satisfactionScore < 80) {
            timeReachedEightyPercent = 301;
        }
        if (((300 - timeRemainingSeconds) - timeReachedEightyPercent) >= 180 && timeReachedEightyPercent != 301) {
            achievements[2] = true;
        }
        previousSatisfactionScore = satisfactionScore;
    }

    public float calculateNewSatisfactionScore(float satisfactionScore) {
        for (int i = 0; i < achievements.length; i++) {
            if (achievements[i] == true) {
                satisfactionScore += AchievementTypes.values()[i].scoreBonus;
            }
        }
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

    public boolean[] getAchievements() {
        return achievements;
    }
}
