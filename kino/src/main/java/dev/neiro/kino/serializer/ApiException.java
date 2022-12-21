package dev.neiro.kino.serializer;

/**
 * @author Wayne Stark
 * @since 18.10.2022
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 4829382930281729372L;

    private int errorCode;

    public ApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return errorCode + " - " + getMessage();
    }
}
