package modelo;


import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Interface implemented by all image format encoding classes.
 *
 * @author Nick Efford
 * @version 1.1 [1999/06/27]
 * @see java.awt.image.BufferedImage
 */

public interface ImageEncoder {
  void encode(BufferedImage image)
   throws IOException, ImageEncoderException;
}

