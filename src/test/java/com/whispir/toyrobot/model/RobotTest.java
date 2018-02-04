/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.model;

import com.whispir.toyrobot.global.Direction;
import com.whispir.toyrobot.global.Position;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Sidhu
 */
public class RobotTest {
    private Robot instance;
    
    @Before
    public void init(){
        instance = new Robot();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    /**
     * Passing Test of placeOnTable method, of class Robot.
     */
    @Test
    public void testPlaceOnTablePass() {
        Position position = new Position(2,4);
        Direction direction = Direction.EAST;
        instance.placeOnTable(position, direction);
        String result = instance.reportPosition();
        String expResult = "2,4,EAST";
        assertEquals(expResult, result);
    }
    
    /**
     * Failing Test of placeOnTable method, of class Robot Position null.
     */
    @Test
    public void testPlaceOnTableFailPositionNull() {
        Position position = null;
        Direction direction = Direction.EAST;
        instance.placeOnTable(position, direction);
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }
    
    /**
     * Failing Test of placeOnTable method, of class Robot Direction null.
     */
    @Test
    public void testPlaceOnTableFailDirectionNull() {
        Position position = new Position(2,4);
        Direction direction = null;
        instance.placeOnTable(position, direction);
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }
    
    /**
     * Failing Test of placeOnTable method, of class Robot Position, Direction
     * both null.
     */
    @Test
    public void testPlaceOnTableFailPositionDirectionNull() {
        Position position = null;
        Direction direction = null;
        instance.placeOnTable(position, direction);
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }

    /**
     * Passing Test of move method, of class Robot.
     */
    @Test
    public void testMovePass() {
        Position position = new Position(2, 1);
        Direction direction = Direction.EAST;
        instance.placeOnTable(position, direction);
        instance.move();
        String result = instance.reportPosition();
        String expResult = "3,1,EAST";
        // Test East facing move
        assertEquals(expResult, result);
        position = new Position(2, 1);
        direction = Direction.WEST;
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = "1,1,WEST";
        // Test West facing move
        assertEquals(expResult, result);
        position = new Position(2, 1);
        direction = Direction.NORTH;
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = "2,2,NORTH";
        // Test North facing move
        assertEquals(expResult, result);
        direction = Direction.SOUTH;
        position = new Position(2, 1);
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = "2,0,SOUTH";
        // Test South facing move
        assertEquals(expResult, result);
    }

    /**
     * Passing Test of move method, of class Robot invalid move ignored
     * to avoid robot falling of tabletop.
     */
    @Test
    public void testInvalidMoveIgnoredPass() {
        Position position = new Position(Position.MINIMUM_GRID_POSITION, Position.MINIMUM_GRID_POSITION);
        Direction direction = Direction.WEST;
        instance.placeOnTable(position, direction);
        instance.move();
        String result = instance.reportPosition();
        String expResult = Position.MINIMUM_GRID_POSITION + "," + Position.MINIMUM_GRID_POSITION + "," + "WEST";
        // Test West facing move
        assertEquals(expResult, result);
        direction = Direction.SOUTH;
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = Position.MINIMUM_GRID_POSITION + "," + Position.MINIMUM_GRID_POSITION + "," + "SOUTH";
        // Test South facing move
        assertEquals(expResult, result);
        position = new Position(Position.GRIDSIZE - 1, Position.GRIDSIZE - 1);
        direction = Direction.NORTH;
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = (Position.GRIDSIZE - 1) + "," + (Position.GRIDSIZE - 1) + "," + "NORTH";
        // Test North facing move
        assertEquals(expResult, result);
        direction = Direction.EAST;
        instance.placeOnTable(position, direction);
        instance.move();
        result = instance.reportPosition();
        expResult = (Position.GRIDSIZE - 1) + "," + (Position.GRIDSIZE - 1) + "," + "EAST";
        // Test East facing move
        assertEquals(expResult, result);
    }

    /**
     * Passing Test of move method, of class Robot invalid move ignored
     * as robot is not placed on tabletop.
     */
    @Test
    public void testMoveIgnoredNotPlacedOnTablePass() {
        instance.move();
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }
    
    /**
     * Passing Test of turnLeft method, of class Robot.
     */
    @Test
    public void testTurnLeftPass() {
        Position position = new Position(Position.MINIMUM_GRID_POSITION, Position.MINIMUM_GRID_POSITION);
        Direction direction = Direction.NORTH;
        instance.placeOnTable(position, direction);
        instance.turnLeft();
        String result = instance.reportPosition();
        String expResult = Position.MINIMUM_GRID_POSITION + "," + Position.MINIMUM_GRID_POSITION + "," + "WEST";
        // Test North facing turn
        assertEquals(expResult, result);
        instance.turnLeft();
        result = instance.reportPosition();
        expResult = expResult.replace("WEST", "SOUTH");
        // Test West facing turn
        assertEquals(expResult, result);
        instance.turnLeft();
        result = instance.reportPosition();
        expResult = expResult.replace("SOUTH", "EAST");
        // Test South facing turn
        assertEquals(expResult, result);
        instance.turnLeft();
        result = instance.reportPosition();
        expResult = expResult.replace("EAST", "NORTH");
        // Test east facing turn
        assertEquals(expResult, result);
    }
    
    /**
     * Passing Test of turnLeft method, of class Robot Invalid turn
     * ignored as it is not placed on tabletop.
     */
    @Test
    public void testTurnLeftIgnoredNotPlacedOnTablePass() {
        instance.turnLeft();
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }
    
    /**
     * Passing Test of turnRight method, of class Robot.
     */
    @Test
    public void testTurnRightPass() {
        Position position = new Position(Position.MINIMUM_GRID_POSITION, Position.MINIMUM_GRID_POSITION);
        Direction direction = Direction.NORTH;
        instance.placeOnTable(position, direction);
        instance.turnRight();
        String result = instance.reportPosition();
        String expResult = Position.MINIMUM_GRID_POSITION + "," + Position.MINIMUM_GRID_POSITION + "," + "EAST";
        // Test North facing turn
        assertEquals(expResult, result);
        instance.turnRight();
        result = instance.reportPosition();
        expResult = expResult.replace("EAST", "SOUTH");
        // Test West facing turn
        assertEquals(expResult, result);
        instance.turnRight();
        result = instance.reportPosition();
        expResult = expResult.replace("SOUTH", "WEST");
        // Test South facing turn
        assertEquals(expResult, result);
        instance.turnRight();
        result = instance.reportPosition();
        expResult = expResult.replace("WEST", "NORTH");
        // Test east facing turn
        assertEquals(expResult, result);
    }
    
    /**
     * Passing Test of turnRight method, of class Robot Invalid turn
     * ignored as it is not placed on tabletop.
     */
    @Test
    public void testTurnRightIgnoredNotPlacedOnTablePass() {
        instance.turnRight();
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
    }

    /**
     * Test of reportPosition method, of class Robot.
     */
    @Test
    public void testReportPosition() {
        String result = instance.reportPosition();
        String expResult = Robot.POSITION_NOT_SET_MSG;
        assertEquals(expResult, result);
        instance.placeOnTable(new Position(1, 2), Direction.NORTH);
        expResult = "1,2,NORTH";
        result = instance.reportPosition();
        assertEquals(expResult, result);
    }
    
}
