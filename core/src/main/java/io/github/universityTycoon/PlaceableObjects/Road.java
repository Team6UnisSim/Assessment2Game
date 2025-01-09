package io.github.universityTycoon.PlaceableObjects;

public class Road extends Terrain {
    
    public Road() {
        super("images/road.png");
        width = 1;
        height = 7;
        satisfactionBonus = -10f;
    }
}
