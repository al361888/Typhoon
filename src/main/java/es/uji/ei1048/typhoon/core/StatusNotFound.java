package es.uji.ei1048.typhoon.core;

public class StatusNotFound extends Exception {
    public StatusNotFound() {
    }

    public StatusNotFound(String message) {
        super(message);
    }

    public StatusNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusNotFound(Throwable cause) {
        super(cause);
    }
}
