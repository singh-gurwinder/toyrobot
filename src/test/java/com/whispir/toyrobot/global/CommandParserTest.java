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
public class CommandParserTest {
    private CommandParser instance;
    
    @Before
    public void init() {
        instance = new CommandParserImpl();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }
    
    /**
     * Passing Test of parse method, of class CommandParser using lower
     * case commands.
     */
    @Test
    public void testParseLowerCasePass() {
        try {
            assertEquals(true, instance.parse("move"));
            assertEquals(true, instance.parse("right"));
            assertEquals(true, instance.parse("left"));
            assertEquals(true, instance.parse("report"));
            assertEquals(true, instance.parse("place 1,2,north"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail("Method failed.");
        }
    }
    
    /**
     * Passing Test of parse method, of class CommandParser using upper
     * case commands.
     */
    @Test
    public void testParseUpperCasePass() {
        try {
            assertEquals(true, instance.parse("MOVE"));
            assertEquals(true, instance.parse("RIGHT"));
            assertEquals(true, instance.parse("LEFT"));
            assertEquals(true, instance.parse("REPORT"));
            assertEquals(true, instance.parse("PLACE 1,2,EAST"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail("Method failed.");
        }
    }
    
    /**
     * Passing Test of parse method, of class CommandParser using mixed
     * case commands.
     */
    @Test
    public void testParseMixedCasePass() {
        try {
            assertEquals(true, instance.parse("Move"));
            assertEquals(true, instance.parse("righT"));
            assertEquals(true, instance.parse("lEFt"));
            assertEquals(true, instance.parse("REporT"));
            assertEquals(true, instance.parse("place 1,2,WEST"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail("Method failed.");
        }
    }
    
    /**
     * Passing Test of parse method, of class CommandParser using mixed
     * case commands with leading/trailing spaces.
     */
    @Test
    public void testParseWithSpasePass() {
        try {
            assertEquals(true, instance.parse("move "));
            assertEquals(true, instance.parse(" RIGHT"));
            assertEquals(true, instance.parse(" lEFt "));
            assertEquals(true, instance.parse(" REporT  "));
            assertEquals(true, instance.parse(" place 1,2,WEST  "));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail("Method failed.");
        }
    }
    
    /**
     * Test of parse method, of class CommandParser using non supported
     * commands or 'PLACE' command with spaces in location or wrong direction.
     */
    @Test
    public void testParseCommandNotSupported() {
        try {
            assertEquals(false, instance.parse("moverobot "));
            assertEquals(false, instance.parse("place 1, 2,WEST"));
            assertEquals(false, instance.parse("place 1 , 2, WEST"));
            assertEquals(false, instance.parse("place 1,2, WEST"));
            assertEquals(false, instance.parse("place 1,2,NORTHEAST"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            fail("Method failed.");
        }
    }
    
    /**
     * Test of parse method, of class CommandParser using 'PLACE' command
     * with X non numeric in location.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testParseXNonNumeric() throws Exception {
        instance.parse("place a,2,EAST");
    }
    
    /**
     * Test of parse method, of class CommandParser using 'PLACE' command
     * with Y non numeric in location.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testParseYNonNumeric() throws Exception {
        instance.parse("place 1,b,NORTH");
    }
    
    /**
     * Test of parse method, of class CommandParser using 'PLACE' command
     * with X, Y non numeric in location.
     * @throws java.lang.Exception
     */
    @Test(expected = Exception.class)
    public void testParseXYNonNumeric() throws Exception {
        instance.parse("place z,g,WEST");
    }
}
