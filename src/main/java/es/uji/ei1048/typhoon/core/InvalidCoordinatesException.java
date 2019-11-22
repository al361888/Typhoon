package es.uji.ei1048.typhoon.core;

public class InvalidCoordinatesException extends Exception{
    public InvalidCoordinatesException() {
    }

    public InvalidCoordinatesException(String message) {
        super(message);
    }

    public InvalidCoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCoordinatesException(Throwable cause) {
        super(cause);
    }
}
