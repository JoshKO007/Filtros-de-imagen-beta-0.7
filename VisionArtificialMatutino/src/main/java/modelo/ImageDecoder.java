package modelo;


import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Interface implemented by all image format decoding classes.
 *
 * @author Nick Efford
 * @version 1.1 [1999/06/27]
 * @see java.awt.image.BufferedImage
 */

public interface ImageDecoder {
  BufferedImage decodeAsBufferedImage()
   throws IOException, ImageDecoderException;
}
