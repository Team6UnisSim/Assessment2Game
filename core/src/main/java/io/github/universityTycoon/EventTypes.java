package io.github.universityTycoon;


/**
 * ADDED IN ASSESSMENT 2
 * EventTypes is an enum class that holds all the information about the different events that can happen during the game.
 * There are 4 negative, 4 positive and 2 neutral.
 * These will have unique effect on satisfaction score and will be triggered at certain time intervals.
 */
public enum EventTypes {
    // Negative events:
    FLOODING(1, "Flooding has occured in the campus.", false),
    HURRICANE(3, "Bad weather has caused damage to the buildings.", false),
    COFFEE_MACHINE_BREAKDOWN(4, "The coffee machine has broken.", false),
    STUDENT_PROTEST(2, "Students protest about poor conditions on campus.", true), 
    // Positive events:
    CELEBRITY_GUEST(2, "A celebrity guest will be giving a lecture in campus.", true),
    FOOTBALL_VICTORY(3, "The university football team beat their local rivals.", false), 
    ANONYMOUS_GRANT(1, "A large anonymous donation has been made to the university.", false), 
    CULTURAL_FAIR(4, "A cultural fair has been organised by the university for the weekend.", true),
    // NeutralEvents:
    GOOD_WEATHER(4, "A few days of good weather have improved everyone's mood.", false),
    GEESE_INVASION(2, "A flock of geese have invaded the campus.", false);
    
    private final String description; // short description of the event
    private final int rarity; // probability of the event occuring -> [1 = rare] -- [4 = common]
    private boolean planned; // whether this event type warns the user


    /**
     * Constructor for EventTypes
     * 
     * @param effect effect on the student satisfactions score
     * @param description short description of the event occuring
     * @param rarity chances of the event occuring
     */
    EventTypes(int rarity, String description, boolean planned){
    
        if (rarity < 1 || rarity > 4){
            throw new IllegalArgumentException("Invalid rarity");         
        }

        this.rarity = rarity;
        this.description = description;
        this.planned = planned;
    }

    /**
     * Get rarity of event
     * @return rarity -> int
     */
    public int getRarity(){
        return rarity;
    }

    /**
     * Get description of event
     * @return description -> String
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get boolean value of planned event
     * @return planned -> boolean
     */
    public boolean getPlanned() {
        return planned;
    }
}
