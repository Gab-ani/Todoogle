package exceptions;

public class WrongTokenException extends Exception {

    private static final long serialVersionUID = 5861563537366287163L;

    public WrongTokenException() {
        super();
    }

    public WrongTokenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WrongTokenException(final String message) {
        super(message);
    }

    public WrongTokenException(final Throwable cause) {
        super(cause);
    }

}