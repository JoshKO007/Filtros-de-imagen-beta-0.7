package modelo;


/**
 * Base class for exceptions thrown by image format encoding classes.
 *
 * @author Nick Efford
 * @version 1.1 [1999/06/27]
 */

public class ImageEncoderException extends Exception {
  public ImageEncoderException() {}
  public ImageEncoderException(String message) { super(message); }
}
