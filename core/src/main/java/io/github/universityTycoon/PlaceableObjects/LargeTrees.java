package io.github.universityTycoon.PlaceableObjects;

/**
 * ADDED IN ASSESSMENT 2
 * LargeTrees extends the Terrain class, and is one of the terrain types that appears on the map.
 */
public class LargeTrees extends Terrain {
    
    public LargeTrees() {
        super("images/large_trees.png");
        width = 2;
        height = 2;
        satisfactionBonus = 10f;
    }
}
