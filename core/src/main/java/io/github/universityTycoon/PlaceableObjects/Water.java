package io.github.universityTycoon.PlaceableObjects;

public class Water extends Terrain {
    
    public Water() {
        super("images/lake.png");
        width = 6;
        height = 4;
        satisfactionBonus = 15f;
    }
}
