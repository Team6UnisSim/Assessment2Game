package io.github.universityTycoon;

import io.github.universityTycoon.PlaceableObjects.Building;
import io.github.universityTycoon.PlaceableObjects.MapObject;
import io.github.universityTycoon.PlaceableObjects.MapObjectPointer;
import io.github.universityTycoon.PlaceableObjects.Event;
import java.util.Random;

import java.time.LocalDateTime;
import java.time.Duration;

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


    // --------- METHODS FOR EVENTS BELOW ---------

    /**
     * Finds a random free tile - no need to iterate over map
     * @return x and y coordinates of the tile
     */
    public int[] findRandomFreeTile(){
        Random random = new Random();

        // attemp up to 100 times to find a free tile
        for (int i = 0; i < 100; i++){
            int x = random.nextInt(tilesWide);
            int y = random.nextInt(tilesHigh);

            if (mapObjects[x][y] == null){ // check if free
                return new int[] {x, y}; 
            }
        }
        return null; 
    }



    /**
     * Finds a random tile to place the event icon to using findRandomFreeTile().
     * Places the object on the MapObject grid on that free tile.
 
     * 
     * @param event event to retrieve icon for
     * @param xPos x position of the event to be placed
     * @param yPos y position of the event to be placed
     * @return
     */
    public boolean placeEventAt(Event event){
        
        // find a free random tile
        int[] freeTile = findRandomFreeTile();
        if (freeTile == null){
            return false;
        }

        int xPos = freeTile[0];
        int yPos = freeTile[1];

        // check that xPos and yPos aren't out of map bounds
        if (xPos < 0 || xPos >= tilesWide || yPos < MIN_DISTANCE_TO_TOP || yPos >= tilesHigh){
            return false; // tile out of bounds
        }

        // Check if tile is occupied
        if (mapObjects[xPos][yPos] != null){
            return false; // tile not free
        }

        // otherwise place the event on the tile
        mapObjects[xPos][yPos] = event;
        return true; // successfully placed
    }



    /**
     * This function checks all the events against the current game time, and removes them.
     * Only one 
     * @param gameTime The current in game time.
     */
    public boolean updateEvent(LocalDateTime gameTime, int[] eventTile) {
        Event event = (Event) mapObjects[eventTile[0]][eventTile[1]]; // retrieve event
        if (event == null){
            return false; // no event exists at this time
        }

        GameEvent gameEvent = event.getGameEvent();
        LocalDateTime timeEventStarted = gameEvent.getEventStartedAt();

        // duration the event has been taking place
        Duration durationOfEvent = Duration.between(timeEventStarted , gameTime);

        // !!! ADD: isEventDealtWith() -> check if the event has been dealth with
        if (durationOfEvent.getSeconds() >= 60){ 
            mapObjects[eventTile[0]][eventTile[1]] = null; // remove event
            return true;
        }
        return false; // event is still ongoing
    }
}