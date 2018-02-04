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
public class CommandParserImpl implements CommandParser {

    @Override
    public boolean parse(String command) throws Exception {
        boolean result = false;
        command = command.trim().toLowerCase();
        if (command.startsWith("place")) {
            String[] cmd = command.split(" ");
            if (cmd.length != 2) {
                result = false;
            } else {
                String[] location = cmd[1].split(",");
                if (location.length != 3) {
                    result = false;
                } else {
                    try {
                        Integer.parseInt(location[0]);
                        Integer.parseInt(location[1]);
                        switch(location[2]) {
                            case "east":
                            case "west":
                            case "north":
                            case "south":
                                result = true;
                                break;
                            default:
                                result = false;
                        }
                        
                    } catch (NumberFormatException ex) {
                        throw ex;
                    }
                }
            }
        } else if (command.equals("move") || command.equals("left")
                || command.equals("right") || command.equals("report")) {
            result = true;
        }
        return result;
    }
}
