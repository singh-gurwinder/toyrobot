/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.global;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Sidhu
 */
public class PositionTest {
    private Position instance;
    
    @Before
    public void init()  {
        instance = new Position();
    }
    
    @After
    public void tearDown()  {
        instance = null;
    }
    
    /**
     * Passing Test of validPosition method, of class Position.
     */
    @Test
    public void testValidPositionPass() {
        boolean expResult = true;
        instance.setX(0);
        instance.setY(0);
        boolean result = instance.validPosition();
        assertEquals(expResult, result);
        instance.setX(4);
        instance.setY(4);
        result = instance.validPosition();
        assertEquals(expResult, result);
        instance.setX(3);
        instance.setY(1);
        result = instance.validPosition();
        assertEquals(expResult, result);
    }
    
    /**
     * Passing Test of validPosition method, of class Position.
     */
    @Test
    public void testValidPositionFail() {
        boolean expResult = false;
        // x less than minimum 
        instance.setX(Position.MINIMUM_GRID_POSITION - 1);
        instance.setY(Position.MINIMUM_GRID_POSITION);
        boolean result = instance.validPosition();
        assertEquals(expResult, result);
        // y less than minimum 
        instance.setX(Position.MINIMUM_GRID_POSITION);
        instance.setY(Position.MINIMUM_GRID_POSITION - 1);
        result = instance.validPosition();
        assertEquals(expResult, result);
        // x & y less than minimum 
        instance.setX(Position.MINIMUM_GRID_POSITION - 1);
        instance.setY(Position.MINIMUM_GRID_POSITION - 1);
        result = instance.validPosition();
        assertEquals(expResult, result);
        // y more than max
        instance.setX(Position.GRIDSIZE - 1);
        instance.setY(Position.GRIDSIZE);
        result = instance.validPosition();
        assertEquals(expResult, result);
        // x more than max
        instance.setX(Position.GRIDSIZE);
        instance.setY(Position.GRIDSIZE - 1);
        result = instance.validPosition();
        assertEquals(expResult, result);
        assertEquals(expResult, result);
        // x & y more than max
        instance.setX(Position.GRIDSIZE);
        instance.setY(Position.GRIDSIZE);
        result = instance.validPosition();
        assertEquals(expResult, result);
        // x less than minimum & y more than max
        instance.setX(Position.MINIMUM_GRID_POSITION - 1);
        instance.setY(Position.GRIDSIZE);
        result = instance.validPosition();
        assertEquals(expResult, result);
        // y less than minimum & x more than max
        instance.setX(Position.GRIDSIZE);
        instance.setY(Position.MINIMUM_GRID_POSITION - 1);
        result = instance.validPosition();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of toString method, of class Position.
     */
    @Test
    public void testToString() {
        instance.setX(1);
        instance.setY(2);
        String expResult = "1,2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
