/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.controller;

import com.whispir.toyrobot.global.CommandParser;
import com.whispir.toyrobot.global.CommandParserImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Sidhu
 */
public class RobotControllerTest {
    private RobotController instance;
    private CommandParser cmdParser;
    
    @Before
    public void init() {
        cmdParser = new CommandParserImpl();
        instance = new RobotControllerImpl(cmdParser);
    }
    
    @After
    public void tearDown() {
        instance = null;
        cmdParser = null;
    }

    /**
     * Reads robot commands from a file removing comments.
     * Comments are the lines that start with a '*'.
     * @param path Path to the file.
     * @return List of robot commands.
     */
    private List<String> readFile(String path) {
        Path file = Paths.get(path);
        List<String> result = new ArrayList();
        try (BufferedReader reader = Files.newBufferedReader(file, Charset.forName("UTF-8"))) {
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (!currentLine.startsWith("*")) {
                    result.add(currentLine.toUpperCase());
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    /**
     * Test of process method, of class RobotController.
     */
    @Test
    public void testProcess() {
        List<String> commands = readFile("src/test/java/com/whispir/toyrobot/controller/test1.txt");
        String result = null;
        String expResult = null;
        for(String command : commands) {
            if (!command.startsWith("OUTPUT")){
                result = instance.process(command);
            } else {
                expResult = command.split(":")[1].trim();
                assertEquals(expResult, result);
            }
        }
    }
    
    /**
     * Test of process method, of class RobotController.
     * Commands before PLACE must be ignored.
     */
    @Test
    public void testProcessNotPlacedOnTable() {
        List<String> commands = readFile("src/test/java/com/whispir/toyrobot/controller/test2.txt");
        String result = null;
        String expResult = null;
        for(String command : commands) {
            if (!command.startsWith("OUTPUT")){
                result = instance.process(command);
            } else {
                expResult = command.split(":")[1].trim();
                assertEquals(expResult, result);
            }
        }
    }
}
