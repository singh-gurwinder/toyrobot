/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.controller;

import com.whispir.toyrobot.global.CommandParser;
import com.whispir.toyrobot.global.Direction;
import com.whispir.toyrobot.global.Position;
import com.whispir.toyrobot.model.Robot;

/**
 *
 * @author Sidhu
 */
public class RobotControllerImpl implements RobotController {
    private final CommandParser cmdParser;
    private final Robot robot;
    
    public RobotControllerImpl(CommandParser cmdParser) {
        this.cmdParser = cmdParser;
        robot = new Robot();
    }

    @Override
    public String process(String command) {
        String result = null;
        try {
            if (cmdParser.parse(command)) {
                command = command.trim().toUpperCase();
                switch (command) {
                    case "LEFT":
                        robot.turnLeft();
                        break;
                    case "RIGHT":
                        robot.turnRight();
                        break;
                    case "MOVE":
                        robot.move();
                        break;
                    case "REPORT":
                        result = robot.reportPosition().toUpperCase();
                        break;
                    default:
                        if (command.startsWith("PLACE")) {
                            String[] cmd = command.split(" ");
                            if (cmd.length == 2) {
                                String[] location = cmd[1].split(",");
                                if (location.length == 3) {
                                    try {
                                        int x = Integer.parseInt(location[0]);
                                        int y = Integer.parseInt(location[1]);
                                        Direction direction = null;
                                        switch(location[2]) {
                                            case "EAST":
                                                direction = Direction.EAST;
                                                break;
                                            case "WEST":
                                                direction = Direction.WEST;
                                                break;
                                            case "NORTH":
                                                direction = Direction.NORTH;
                                                break;
                                            case "SOUTH":
                                                direction = Direction.SOUTH;
                                                break;
                                            default:
                                                break;
                                        }
                                        if (direction != null) {
                                            Position position = new Position(x, y);
                                            robot.placeOnTable(position, direction);
                                            result = robot.reportPosition().toUpperCase();
                                        }
                                    } catch (NumberFormatException ex) {
                                        throw ex;
                                    }
                                }
                            }
                        }
                }
            }
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        return result;
    }
}
