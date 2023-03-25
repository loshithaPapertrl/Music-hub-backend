/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papertrl.springsecurity.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author Dimuthu
 */
@Data
@NoArgsConstructor
public class ExceptionMessage implements Serializable {

    private String message;

    public ExceptionMessage(String message) {
        this.message = message;
    }

}
