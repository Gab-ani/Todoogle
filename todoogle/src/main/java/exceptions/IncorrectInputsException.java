package exceptions;

public final class IncorrectInputsException extends Exception {

    private static final long serialVersionUID = 5861110537316287163L;

    public IncorrectInputsException() {
        super();
    }

    public IncorrectInputsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IncorrectInputsException(final String message) {
        super(message);
    }

    public IncorrectInputsException(final Throwable cause) {
        super(cause);
    }

}
