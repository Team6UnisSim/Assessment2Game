package io.github.universityTycoon.PlaceableObjects;

/**
 * ADDED IN ASSESSMENT 2
 * Water extends the Terrain class, and is one of the terrain types that appears on the map.
 */
public class Water extends Terrain {
    
    public Water() {
        super("images/lake.png");
        width = 6;
        height = 4;
        satisfactionBonus = 15f;
    }
}
