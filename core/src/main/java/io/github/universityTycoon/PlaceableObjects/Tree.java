package io.github.universityTycoon.PlaceableObjects;

/**
 * ADDED IN ASSESSMENT 2
 * Tree extends the Terrain class, and is one of the terrain types that appears on the map.
 */
public class Tree extends Terrain {
    
    public Tree() {
        super("images/tree.png");
        satisfactionBonus = 5f;
    }
}
