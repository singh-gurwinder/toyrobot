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
public interface CommandParser {
    /**
     * Parses the string command.
     * @param command The command to be parsed.
     * @return true if command is supported.
     * @throws Exception 
     */
    public boolean parse(String command) throws Exception;
}
