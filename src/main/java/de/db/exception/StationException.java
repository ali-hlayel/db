package de.db.exception;

public class StationException extends RuntimeException {
    public StationException(StationError error) {
        super(error.name());
    }
}
