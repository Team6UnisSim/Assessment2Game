package io.github.universityTycoon;

import java.util.ArrayList;

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
    }
}