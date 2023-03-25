/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papertrl.springsecurity.exception;

/**
 *
 * @author Dimuthu
 */
public class MusicHubCheckedException extends Exception {

    public MusicHubCheckedException(String errorMessage) {
        super(errorMessage);
    }

    public MusicHubCheckedException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public MusicHubCheckedException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
