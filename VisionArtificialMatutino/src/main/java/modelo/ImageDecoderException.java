package modelo;


/**
 * Base class for exceptions thrown by image format decoding classes.
 *
 * @author Nick Efford
 * @version 1.1 [1999/06/27]
 */

public class ImageDecoderException extends Exception {
  public ImageDecoderException() {}
  public ImageDecoderException(String message) { super(message); }
}
