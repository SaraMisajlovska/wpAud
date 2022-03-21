package mk.finki.ukim.wpaud.model.exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super("Invalid Username Or Password Exception");
    }
}
