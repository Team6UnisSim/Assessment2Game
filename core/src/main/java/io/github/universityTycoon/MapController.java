package io.github.universityTycoon;

import io.github.universityTycoon.PlaceableObjects.Building;
import io.github.universityTycoon.PlaceableObjects.MapObject;
import io.github.universityTycoon.PlaceableObjects.MapObjectPointer;
import io.github.universityTycoon.PlaceableObjects.Event;
import java.util.Random;

import java.time.LocalDateTime;

/**
 * Controls the map.
 *
 * @param tilesWide The number of tiles wide the map is.
 * @param tilesHigh The number of tiles high the map is.
 * @param mapObjects A 2D array of the placeable objects on the map.
 * @param MIN_DISTANCE_TO_TOP The min distance an object has to be from the top to be placed.
 */
public class MapController {
    private int tilesWide;
    private int tilesHigh;
    MapObject[][] mapObjects;
    private final int MIN_DISTANCE_TO_TOP = 2;


    /**
     * Constructor taking the following parameters.
     *
     * @param tilesWide The number of tiles wide the map is.
     * @param tilesHigh The number of tiles high the map is.
     */
    public MapController(int tilesWide, int tilesHigh) {
        this.tilesWide = tilesWide;
        this.tilesHigh = tilesHigh;
        this.mapObjects = new MapObject[tilesWide][tilesHigh];

    }

    /**
     * Adds a building to the map, if the attempted location is acceptable. Meaning it doesn't overlap with something else.
     *
     * @param building The building being added.
     * @param xPos The x grid coordinate it's being placed in.
     * @param yPos The y grid coordinate it's being placed in.
     * @return true if the building was successfully added, false otherwise.
     */
    public boolean addObject(MapObject object, int xPos, int yPos) {
        boolean objectFits = true;
        // Note that the top left square is 0,0, so y/j is negative
        for (int i = 0; i < object.getWidth() ; i++) {
            for (int j = 0; j < object.getHeight() ; j++) {
                if(object instanceof Building) {
                    if ((xPos + i >= tilesWide || yPos - j < MIN_DISTANCE_TO_TOP || yPos - j >= tilesHigh) || mapObjects[xPos + i][yPos - j] != null) {
                        objectFits = false;
                        break;
                    }
                } else {
                    if ((xPos + i >= tilesWide || yPos - j >= tilesHigh) || mapObjects[xPos + i][yPos - j] != null) {
                        objectFits = false;
                        break;
                    }
                }
            }
        }
        if (objectFits) {
            // Place the bottom left square
            mapObjects[xPos][yPos] = object;

            // Then place pointers to the original
            for (int i = 0; i < object.getWidth() ; i++) {
                for (int j = 0; j < object.getHeight() ; j++) {
                    if (i != 0 || j != 0) {
                        mapObjects[xPos + i][yPos - j] = new MapObjectPointer(object);
                    }
                }
            }
        }
        return objectFits;
    }

    public void removeObject(MapObject object, int xPos, int yPos) {
        // Remove the bottom left square
        mapObjects[xPos][yPos] = null;

        // Then remove the pointers to the original
        for (int i = 0; i < object.getWidth() ; i++) {
            for (int j = 0; j < object.getHeight() ; j++) {
                if (i != 0 || j != 0) {
                    mapObjects[xPos + i][yPos - j] = null;
                }
            }
        }
    }

    /**
     * This function checks all the buildings against the current game time, and updates them when they've finished
     * being constructed. Call this to ensure buildings progress from under construction to complete
     * @param gameTime The current in game time.
     */
    public void updateBuildings(LocalDateTime gameTime) {
        for (int x = 0; x < tilesWide; x++) {
            for (int y = 0; y < tilesHigh; y++) {
                if (mapObjects[x][y] instanceof Building) {
                    ((Building) mapObjects[x][y]).update(gameTime);
                }
            }
        }
    }


    // --------- METHODS FOR EVENTS BELOW ---------

    /**
     * Finds a random free tile - no need to iterate over map
     * @return x and y coordinates of the tile
     */
    public int[] findRandomFreeTile(){
        Random random = new Random();
        int x = 0;
        int y = 0;

        // attemp up to 100 times to find a free tile
        for (int i = 0; i < 100; i++){
            x = random.nextInt(tilesWide);
            y = random.nextInt(tilesHigh);

            if (mapObjects[x][y] == null){ // check if occupied
                // if tile unoccupied return x,y
                return new int[] {x, y};
            }
        }
        System.out.println("Tile already occupied."); 
        return null;
    }



    /**
     * Finds a random tile to place the event icon using helper method above
     * Places the object on the MapObject grid on that free tile.
 
     * 
     * @param event event to retrieve icon for
     * @return x and y coordinates of the tile that event was placed on
     */
    public int[] placeEvent(GameEvent event){

        Event eventToPlace = new Event(event);
        // find a random free tile
        int[] freeTile = findRandomFreeTile();
        if (freeTile == null){
            return null;
        }

        int x = freeTile[0];
        int y = freeTile[1];

        mapObjects[x][y] = eventToPlace;
        return freeTile; // return free tile
    }

    /**
     * This function checks all the buildings against the current game time, and updates them when they've finished
     * being constructed. Call this to ensure buildings progress from under construction to complete
     * @param gameTime The current in game time.
     */
    public void updateEvents(LocalDateTime gameTime) {
        for (int x = 0; x < tilesWide; x++) {
            for (int y = 0; y < tilesHigh; y++) {
                if (mapObjects[x][y] instanceof Event) {
                    ((Event) mapObjects[x][y]).getGameEvent().updateEvent(gameTime, x, y, mapObjects);
                }
            }
        }
    }
}