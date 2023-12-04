package modelo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFile implements ImageDecoder {

    private File imageFile;

    public ImageFile(String filePath) {
        this.imageFile = new File(filePath);
    }

    @Override
    public BufferedImage decodeAsBufferedImage() throws IOException, ImageDecoderException {
        if (!imageFile.exists()) {
            throw new ImageDecoderException("El archivo de imagen no existe.");
        }

        try {
            // Intenta cargar la imagen desde el archivo
            BufferedImage image = ImageIO.read(imageFile);

            if (image == null) {
                throw new ImageDecoderException("No se pudo cargar la imagen.");
            }

            return image;
        } catch (IOException e) {
            throw new ImageDecoderException("Error al leer el archivo de imagen: " + e.getMessage());
        }
    }

    public static ImageDecoder createImageDecoder(String filePath) {
        return new ImageFile(filePath);
    }
}