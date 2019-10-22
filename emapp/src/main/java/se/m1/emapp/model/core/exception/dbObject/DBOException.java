package se.m1.emapp.model.core.exception.dbObject;

import se.m1.emapp.model.core.exception.DatabaseCommunicationException;

public abstract class DBOException extends DatabaseCommunicationException {
    public DBOException() {
    }

    public DBOException(String message) {
        super(message);
    }

    public DBOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBOException(Throwable cause) {
        super(cause);
    }

    public DBOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}