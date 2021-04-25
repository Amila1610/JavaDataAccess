package one.exception;

public class DaoException extends Exception {

    public DaoException(String message){
        super("Dao problem: " + message);
    }
}
