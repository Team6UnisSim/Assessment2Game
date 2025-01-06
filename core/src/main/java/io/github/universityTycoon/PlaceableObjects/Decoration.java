package io.github.universityTycoon.PlaceableObjects;

import java.time.LocalDateTime;

/**
 * A generic decorative object to be placed on the map. May affect satisfaction
 */
public class Decoration extends Building {
    
    // add random textures for decoration, will need to adjust other methods
    public Decoration(LocalDateTime constructionStartedAt, String texturePath) {
        super(constructionStartedAt, texturePath);
        satisfactionBonus = 10f;
    }

    public String getName() {
        return name;
    }
    public String getTexturePath() {
        return texturePath;
    }

    public boolean getIsStackable() {
        return isStackable;
    }

    public float calculateSatisfaction(int ownX, int ownY, MapObject[][] mapObjects) { // As a percentage
        return 0f;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getSatisfactionBonus() {
        return satisfactionBonus;
    }

}
