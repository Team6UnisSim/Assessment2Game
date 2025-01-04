package io.github.universityTycoon;

/** Things that can affect the satisfaction. These are used to calculate satisfaction.
 *  For instance, if the game has the modifier "Flooding", buildings near water may have reduced effectiveness
 */
public enum GameModifiers {
    // Negative Modifiers
    FLOODING(-3, "Reduces satisfaction - flooding"),
    HURRICANE(-2, "Reduces satisfaction - hurricane"),
    POWER_OUTAGE(-5, "Reduces satisfaction - power outage in a specific building"),
    STUDENT_PROTEST(-1, "Reduces satisfaction - poor campus conditions"),
    // Positive Modifiers
    CELEBRITY_GUEST(4, "Increases satisfaction - celebrity guest lecture"),
    FOOTBALL_VICTORY(2, "Increases satisfaction - university team winning a game against another university"),
    ANONYMOUS_GRANT(3, "Increases satisfaction - receipt of large sum of money from anonymous donor"),
    GOOD_WEATHER(1, "Increases satisfaction - some days of good weather");


    private final int effect; // numerical value of the modifier on the score
    private final String description; // description of the modifier


    /**
     * Constructor for GameModifiers
     * 
     * @param effect        numerical value of the modifier on the score
     * @param description   description of the modifier
     */
    GameModifiers(int effect, String description){
        this.effect = effect;
        this.description = description;
    }


    /**
     * Retrieves effect of modifier
     * @return numerical effect
     */
    public int getEffect() {
        return effect;
    }


    /**
     * Retrieves description of modifier
     * @return description of modifier
     */
    public String getDescription() {
        return description;
    }
}