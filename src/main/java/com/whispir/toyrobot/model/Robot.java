/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.model;

import com.whispir.toyrobot.global.Position;
import com.whispir.toyrobot.global.Direction;

/**
 *
 * @author Sidhu
 */
public class Robot {
    private Position position;
    private Direction direction;
    private boolean onTable;
    private static final String INVALID_POSITION_MSG = "Invalid position.";
    private static final String INVALID_MOVE_MSG = "Move not possible. Invalid position.";
    public static final String POSITION_NOT_SET_MSG = "Robot not placed on table.";
    
    public Robot() {
        onTable = false;
    }
    
    /**
     * Sets the position of robot on table if position is valid.
     * @param position Position to be placed on table.
     * @param direction Direction of robot face.
     */
    public void placeOnTable(Position position, Direction direction) {
        if (position != null && position.validPosition() && direction != null) {
            this.position = position;
            this.direction = direction;
            if (onTable == false){
                onTable = true;
            }
        } else {
            System.out.println(INVALID_POSITION_MSG);
        }
    }
    
    /**
     * Moves robot one unit forward in the direction it is facing if
     * it is on table and new position is valid.
     */
    public void move() {
        if (onTable) {
            if (direction == Direction.NORTH && position.getY() < Position.GRIDSIZE - 1) {
                position.setY(position.getY() + 1);
            } else if(direction == Direction.SOUTH && position.getY() > Position.MINIMUM_GRID_POSITION) {
                position.setY(position.getY() - 1);
            } else if (direction == Direction.EAST && position.getX() < Position.GRIDSIZE - 1) {
                position.setX(position.getX() + 1);
            } else if(direction == Direction.WEST && position.getX() > Position.MINIMUM_GRID_POSITION) {
                position.setX(position.getX() - 1);
            } else {
                System.out.println(INVALID_MOVE_MSG);
            }
        }
    }
    
    /**
     * Turns the robot to left direction if it is on table.
     */
    public void turnLeft() {
        if (onTable) {
            switch (direction) {
                case NORTH:
                    direction = Direction.WEST;
                    break;
                case SOUTH:
                    direction = Direction.EAST;
                    break;
                case EAST:
                    direction = Direction.NORTH;
                    break;
                case WEST:
                    direction = Direction.SOUTH;
                    break;
            }
        }
    }
    
    /**
     * Turns the robot to right direction if it is on table.
     */
    public void turnRight() {
        if (onTable) {
            switch (direction) {
                case NORTH:
                    direction = Direction.EAST;
                    break;
                case SOUTH:
                    direction = Direction.WEST;
                    break;
                case EAST:
                    direction = Direction.SOUTH;
                    break;
                case WEST:
                    direction = Direction.NORTH;
                    break;
            }
        }
    }
    
    /**
     * Reports current position and direction of robot.
     * @return Robot position and direction as String.
     */
    public String reportPosition() {
        return onTable ? String.format("%s,%s", position.toString(), direction.toString()) : POSITION_NOT_SET_MSG;
    }
}
