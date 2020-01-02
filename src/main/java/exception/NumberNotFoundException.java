package exception;

public class NumberNotFoundException extends Exception {
    public NumberNotFoundException() {}
    public NumberNotFoundException(String message) {
        super(message);
    }
}
