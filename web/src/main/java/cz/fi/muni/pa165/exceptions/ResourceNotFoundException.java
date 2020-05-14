package java.cz.fi.muni.pa165.exceptions;

/**
 * Exception converted by MyExceptionHandler to NOT_FOUND HTTP status.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
