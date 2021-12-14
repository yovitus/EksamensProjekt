package Exceptions;

public class UserExistException extends Exception {
    public UserExistException()
    {
        super("USERNAME ALREADY EXIST");
    }
}
