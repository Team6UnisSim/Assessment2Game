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
    public boolean addBuilding(Building building, int xPos, int yPos) {
        boolean buildingFits = true;
        // Note that the top left square is 0,0, so y/j is negative
        for (int i = 0; i < building.getSize() ; i++) {
            for (int j = 0; j < building.getSize() ; j++) {
                if ((xPos + i >= tilesWide || yPos - j < MIN_DISTANCE_TO_TOP || yPos - j >= tilesHigh) || mapObjects[xPos + i][yPos - j] != null) {
                    buildingFits = false;
                    break;
                }
            }
        }
        if (buildingFits) {
            // Place the bottom left square
            mapObjects[xPos][yPos] = building;

            // Then place pointers to the original
            for (int i = 0; i < building.getSize() ; i++) {
                for (int j = 0; j < building.getSize() ; j++) {
                    if (i != 0 || j != 0) {
                        mapObjects[xPos + i][yPos - j] = new MapObjectPointer(building);
                    }
                }
            }
        }
        return buildingFits;
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



    /**
     * Adds event icon to the specified position given
     * 
     * @param event event to retrieve icon for
     * @param xPos x position of the event to be placed
     * @param yPos y position of the event to be placed
     * @return
     */
    public boolean addEventIcon(Event event, int xPos, int yPos){
        
        // Check if it x,y are within the map bounds and that the tile is free
        if (xPos < 0 || xPos >= tilesWide || yPos < MIN_DISTANCE_TO_TOP || yPos >= tilesHigh){
            return false; // position passed is out of bounds
        }

        // Check that tile is free
        if (mapObjects[xPos][yPos] != null){
            return false; // tile is not free
        }
        // Place the event on the single tile (no MapObjectPointers needed)
        mapObjects[xPos][yPos] = event;
        return true;
    }



    // /**
    //  * This function checks all the events against the current game time, and updates them when they've finished.
    //  * Call this to ensure events progress from current to complete.
    //  * @param gameTime The current in game time.
    //  */
    // public void updateEvents(LocalDateTime gameTime) {
    //     for (int x = 0; x < tilesWide; x++) {
    //         for (int y = 0; y < tilesHigh; y++) {
    //             if (mapObjects[x][y] instanceof Event) {
    //                 ((GameEvent) mapObjects[x][y]).update(gameTime);
    //             }
    //         }
    //     }
    // }


    /**
     * Method for finding a random tile that is free to display the event icon on
     * @return x and y coordinates of the tile
     */
    public int[] findRandomFreeTile() throws Exception{

        Random random = new Random();

        // attemp up to 100 times to find a free tile to place event icon
        for (int i = 0; i < 100; i++){
            // get random coordinates
            int x = random.nextInt(tilesWide);
            int y = random.nextInt(tilesHigh);

            // check if free
            if (mapObjects[x][y] == null){
                int[] coordinates = new int[2]; // create array to hold 2 coordinates
                coordinates[0] = x;
                coordinates[1] = y;
                return coordinates;
            }
        }
        throw new Exception("No free tile found");
    }
}
