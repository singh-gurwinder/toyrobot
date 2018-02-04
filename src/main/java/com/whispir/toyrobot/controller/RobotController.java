/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.whispir.toyrobot.controller;

/**
 *
 * @author Sidhu
 */
public interface RobotController {
    /**
     * Process the command.
     * @param command Command to be processed.
     * @return String result or null.
     */
    public String process(String command);
}
