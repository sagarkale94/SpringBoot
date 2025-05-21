package co.in.sagarkale.responseentityexceptionhandler.exceptions;

public class UserInActiveException extends RuntimeException{
    public UserInActiveException(String message) {
        super(message);
    }
}
