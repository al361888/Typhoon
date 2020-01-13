package es.uji.ei1048.typhoon.core.exception;

public class NoCityFoundException extends Exception{
    public NoCityFoundException() {
    }

    public NoCityFoundException(String message) {
        super(message);
    }

    public NoCityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCityFoundException(Throwable cause) {
        super(cause);
    }
}
