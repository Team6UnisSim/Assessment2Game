package io.github.universityTycoon;


/**
 * ADDED IN ASSESSMENT 2
 * EventTypes is an enum class that holds all the information about the different events that can happen during the game.
 * There are 4 negative, 4 positive and 2 neutral.
 * These will have unique effect on satisfaction score and will be triggered at certain time intervals.
 */
public enum EventTypes {
    // Negative events:
    FLOODING(1, "Flooding has occured in the campus.", "images/tree.png", false),
    HURRICANE(3, "Bad weather has caused damage to the buildings.", "images/tree.png", false),
    COFFEE_MACHINE_BREAKDOWN(4, "The coffee machine has broken.", "images/tree.png", false),
    STUDENT_PROTEST(2, "Students protest about poor conditions on campus.", "images/tree.png", true), 
    // Positive events:
    CELEBRITY_GUEST(2, "A celebrity guest will be giving a lecture in campus.", "images/tree.png", true),
    FOOTBALL_VICTORY(3, "The university football team beat their local rivals.", "images/tree.png", false), 
    ANONYMOUS_GRANT(1, "A large anonymous donation has been made to the university.", "images/tree.png", false), 
    CULTURAL_FAIR(4, "A cultural fair has been organised by the university for the weekend.", "images/tree.png", true),
    // NeutralEvents:
    GOOD_WEATHER(4, "A few days of good weather have improved everyone's mood.", "images/tree.png", false),
    GEESE_INVASION(2, "A flock of geese have invaded the campus.", "images/tree.png", false);
    
    private final String description; // short description of the event
    private final int rarity; // probability of the event occuring -> [1 = rare] -- [4 = common]
    private final String iconPath; // the file path for that event-specific icon to be displayed
    private boolean planned; // whether this event type warns the user


    /**
     * Constructor for EventTypes
     * 
     * @param effect effect on the student satisfactions score
     * @param description short description of the event occuring
     * @param rarity chances of the event occuring
     * @param icon the file path for that event-specific icon to be displayed
     */
    EventTypes(int rarity, String description, String iconPath, boolean planned){
    
        if (rarity < 1 || rarity > 4){
            throw new IllegalArgumentException("Invalid rarity");         
        }
        if (iconPath == null){
            throw new IllegalArgumentException("Invalid icon file path");
        }

        this.rarity = rarity;
        this.description = description;
        this.iconPath = iconPath;
        this.planned = planned;
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

    public boolean getPlanned() {
        return planned;
    }
}
