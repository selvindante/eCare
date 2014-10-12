package ru.tsystems.tsproject.ecare;

/**
 * Created by Selvin
 * on 08.10.2014.
 */
public class ECareException extends RuntimeException {
    String message;

    public ECareException(String message) {
        super(message);
        this.message = message;
    }

    public ECareException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
