package exceptions;

/**
 * @author John S. (jspyronis@tacitknowledge.com)
 *         Date: 12/11/2013
 *         Time: 11:58
 */
public class InvalidMessageException extends Exception
{
    public InvalidMessageException(final String message)
    {
        super(message);
    }
}
