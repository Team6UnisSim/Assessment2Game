package io.github.universityTycoon;

/**
 * Types of eents that can occur in the game. There are 4 negative ad 4 positive ones.
 * These will have unique effects on satisfaction score and will be triggered at different frequencies
 * based on their rarity value.
 */
public enum EventTypes {
    // Negative events:
    Flooding, // -score
    Hurricane, // -score -money(?) - will need to add this
    PowerOutage, // disables the score for the building it is applied to
    StudentProtest, // -score
    // Positive events:
    CelebrityGuest, // +score
    FootballVictory, // +score
    AnonynousGrant, // +money(?) - will need to add this
    GoodWeather // +score
}
