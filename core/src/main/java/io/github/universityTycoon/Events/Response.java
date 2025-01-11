package io.github.universityTycoon.Events;


/**
 * EventResponse class that holds the response attributes
 */
public class Response {
    private float effect; // represented as a percentage increase/decrease (e.g., -10%, +30%)
    private String description;


    public Response(int effect, String description){
        if (description == null ){
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.effect = effect;
        this.description = description;
    }

    public float getEffect(){
        return this.effect;
    }

    public String getDescription(){
        return this.description;
    }
}