package co.in.sagarkale.globalexceptionhandling.exceptions;

public class UserInActiveException extends RuntimeException{
    public UserInActiveException(String message) {
        super(message);
    }
}
