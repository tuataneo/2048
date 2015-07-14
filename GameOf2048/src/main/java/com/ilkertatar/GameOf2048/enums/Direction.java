package com.ilkertatar.GameOf2048.enums;
/**
 * @author ilker Tatar
 * 
 */
public enum Direction {
    /**
     * Move Up
     */
    UP("w","Up"),    
    
    /**
     * Move Right
     */
    RIGHT("d","Right"), 
    
    /**
     * Move Down
     */
    DOWN("s","Down"), 
    
    /**
     * Move Left
     */
    LEFT("a","Left");
    
    
    /**
     * The numeric code of the status
     */
    private final String code;
    
    /**
     * The description of the status
     */
    private final String description;
    
    /**
     * Constructor
     * 
     * @param code
     * @param description 
     */
    private Direction(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
    
    /**
     * Getter for code.
     * 
     * @return 
     */
    public String getCode() {
        return code;
    }
 
    /**
     * Getter for description.
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Overloads the toString and returns the description of the move.
     * @return 
     */
    @Override
    public String toString() { 
        return description;
    }
}
