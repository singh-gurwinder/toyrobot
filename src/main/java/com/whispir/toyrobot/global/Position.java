/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.global;

/**
 *
 * @author Sidhu
 */
public class Position {
    private int x;
    private int y;
    public static final int GRIDSIZE = 5; // Grid size
    public static final int MINIMUM_GRID_POSITION = 0; // Minimum Grid position

    public Position() {
        
    }
    
    /*
        @param the x position
        @param the y position
    */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Checks if position is valid for a GRIDSIZE x GRIDSIZE square tabletop.
     * @return true if position is valid. false otherwise.
     */
    public boolean validPosition() {
        return x >= MINIMUM_GRID_POSITION && x < GRIDSIZE && y >= MINIMUM_GRID_POSITION && y < GRIDSIZE;
    }
    
    @Override
    public String toString() {
        return String.format("%d,%d", x, y);
    }
}
