package io.github.universityTycoon.Events;

/**
 * ADDED IN ASSESSMENT 2
 * Used to store data associated with a response a player can choose for an event.
 * 
 * @param effect the increase/decrease of the player's satisfaction percentage if this response is chosen. 
 * @param description the text description of what the user is choosing to do.
 */
public class Response {
    
    private float effect; 
    private String description;

    /**
     * Constructor taking the following parameters.
     * 
     * @param effect the effect on the user score.
     * @param description the description of the event response.
     */
    public Response(int effect, String description){
        if (description == null ){
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.effect = effect;
        this.description = description;
    }

    /**
     * Returns the score effect.
     * @return the increase/decrease of the player's satisfaction percentage if this response is chosen. 
     */
    public float getEffect(){
        return this.effect;
    }

    /**
     * Returns the description of the response.
     * @return the text description of what the user is choosing to do.
     */
    public String getDescription(){
        return this.description;
    }
}
