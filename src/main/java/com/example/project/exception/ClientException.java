package com.example.project.exception;

/**
 * class ClientException
 * use for calling exception
 *
 * @author Kate Shkuratova
 * @version 1.0
 */
public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
