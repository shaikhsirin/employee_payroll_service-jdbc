/**
 * Create class DBException DBException extends Exception
 *
 * @author Sirin
 *
 */

public class DBException extends Exception {

    public enum ExceptionType {
        CONNECTION_FAIL, SQL_ERROR, UPDATE_ERROR, INVALID_PAYROLL_DATA, RETRIEVE_ERROR
    }

    /**
     * Create ExceptionType is String message, ExceptionType type
     */
    ExceptionType type;

    public DBException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}