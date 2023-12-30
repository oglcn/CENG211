package Exceptions;

public class NotAUniqueNameException extends Exception {
    public NotAUniqueNameException(String message) {
        super(message);
    }
}