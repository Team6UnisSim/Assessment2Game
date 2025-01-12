package io.github.universityTycoon.PlaceableObjects;

/**
 * ADDED IN ASSESSMENT 2
 * Road extends the Terrain class, and is one of the terrain types that appears on the map.
 */
public class Road extends Terrain {
    
    public Road() {
        super("images/road.png");
        width = 1;
        height = 7;
        satisfactionBonus = -10f;
    }
}
