package ru.tsystems.tsproject.ecare;

/**
 * Created by Selvin
 * on 08.10.2014.
 */
public class ECareException extends RuntimeException {


    public ECareException(String message) {
        super(message);
    }

    public ECareException(String message, Throwable e) {
        super(message, e);
    }

}
