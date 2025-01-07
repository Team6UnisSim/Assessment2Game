package io.github.universityTycoon;

/**
 * EventTypes is an enum class that holds all the information about the different events that can happen during the game.
 * There are 4 negative, 4 positive and 2 neutral.
 * These will have unique effect on satisfaction score and will be triggered at certain time intervals.
 */
public enum EventType {
    // Negative events:
    FLOODING(-3, 1, "Flooding has occured in the campus.", "images/flooding.png"),
    HURRICANE(-2, 2, "Bad weather has caused damange to the buildings.", "images/hurricane.png"),
    POWER_OUTAGE(-4, 4, "A power outage has disabled one of your buildings.", "images/power_outage.png"),
    STUDENT_PROTEST(-1, 3, "Students protest about the campus poor conditions.", "images/student_protest.png"), 
    // Positive events:
    CELEBRITY_GUEST(1, 2, "A celebrity guest will be giving a lecture in campus", "images/celebrity_guest.png" ),
    FOOTBALL_VICTORY(3, 3, "The university football team beat the neighbouring university team", "images/football_victory.png"), 
    ANONYMOUS_GRANT(2, 1, "An anonymouys donot has donated a large amount of money to the University", "images/anonymous_grant.png"), 
    CULTURAL_FAIR(4, 4, "A cultural fair has been organised by the  university for the weekend", "images/cultural_fair.png"),
    // NeutralEvents:
    GOOD_WEATHER(0, 4, "A few days of good weather have improved everyone's mood", "images/good_weather.png"),
    CAFE_MACHINE_UPGRADE(0,2, "The library cafe machine has been upgraded.", "images/cafe_machine_upgrade.png");
    
    private final int effect; // effect the event has on the satisfaction score
    private final String description; // short description of the event
    private final int rarity; // probability of the event occuring -> 1:rare - 4:common
    private final String iconPath; // the file path for that event-specific icon to be displayed


    /**
     * Constructor for EventTypes
     * 
     * @param effect effect on the student satisfactions score
     * @param description short description of the event occuring
     * @param rarity chances of the event occuring
     * @param iconPath the file path for that event-specific icon to be displayed
     */
    EventType(int effect, int rarity, String description, String iconPath){
    
        if (rarity < 1 || rarity > 4){
            throw new IllegalArgumentException("Invalid rarity");         
        }
        if (iconPath == null){
            throw new IllegalArgumentException("Invalid iconPath");
        }

        this.effect = effect;
        this.rarity = rarity;
        this.description = description;
        this.iconPath = iconPath;
    }

    public int getEffect(){
        return effect;
    }

    public int getRarity(){
        return rarity;
    }

    public String getDescription(){
        return description;
    }

    public String getIconPath(){
        return iconPath;
    }
}
