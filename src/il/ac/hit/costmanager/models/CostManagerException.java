package il.ac.hit.costmanager.models;

/**
 * This class describes CostManagerException.
 * @author Ziv Hochman, Anzor Torikashvili.
 */
public class CostManagerException extends Exception
{
    /**
     * Presents an error message.
     * @param message Message to display to the user.
     */
    public CostManagerException(String message) {
        super(message);
    }
    /**
     * Presents an error message.
     * @param message Message to display to the user.
     * @param cause Contains the reason  for the exception.
     */
    public CostManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
