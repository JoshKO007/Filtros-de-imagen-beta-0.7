package modelo;


/**
 * Exception thrown by ImageFFT objects.
 *
 * @author Nick Efford
 * @version 1.1 [1999/08/01]
 * @see ImageFFT
 * @see OperationException
 */

public class FFTException extends OperationException {
  public FFTException() {}
  public FFTException(String message) { super(message); }
}
