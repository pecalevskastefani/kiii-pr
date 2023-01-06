package mk.ukim.finki.wpaud.model.exceptions;

public class PasswordsDoNotMatchExceptions extends RuntimeException {
    public PasswordsDoNotMatchExceptions() {
        super("Passwords do not match exception");
    }
}
