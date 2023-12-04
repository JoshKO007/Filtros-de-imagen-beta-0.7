package modelo;

public class HistogramException extends Exception {

    public HistogramException() {
        super();
    }

    public HistogramException(String message) {
        super(message);
    }

    public HistogramException(String message, Throwable cause) {
        super(message, cause);
    }

    public HistogramException(Throwable cause) {
        super(cause);
    }
}