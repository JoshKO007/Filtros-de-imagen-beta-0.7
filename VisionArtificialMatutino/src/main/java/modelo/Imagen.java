package modelo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author antoniocuevas
 */
public class Imagen implements Cloneable{
    
    //Atributos de la clase
    private String nombre;
    private String formato;
    private int filas;
    private int columnas;
    private short[][] matrizGris;
    private short[][] matrizR;
    private short[][] matrizG;
    private short[][] matrizB;
    private BufferedImage bufferImagen;
    private File archivoImagen;
    
    /** Constructor por defecto, crea una imagen con todos sus atributos
     *  con valores nulos.
     */
    public Imagen(){
    }
    
    /** Constructor que tiene como argumento la ruta del archivo de
     * la imagen que se desea cargar.
     * @return null
     */
    public Imagen(File rutaImagen){
        bufferImagen = null;
        try{
            bufferImagen = ImageIO.read(rutaImagen);
            cargarRGB(bufferImagen);
            cargarGris(this.matrizR, this.matrizG, this.matrizB); //¿Como pasar los parametros de las matrices?
        }catch(IOException ex){
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE,null,ex);
        }
        archivoImagen = rutaImagen;
        filas = bufferImagen.getHeight();
        columnas = bufferImagen.getWidth();
        nombre = rutaImagen.getName();
        formato = obtenerFormato(rutaImagen);
        System.out.println("Imagen::Termina carga");
    }
    
    /**
     * Método que realiza la lectura del contenido del
     * BufferedImage. Construye 3 matrices que representan
     * los píxeles en cada canal de color
     * @param BufferedImage
     * @throws IOException
     */
    
    private void cargarRGB(BufferedImage imagenOriginal){
        System.out.println("Alto: " + imagenOriginal.getHeight());
        System.out.println("Ancho: " + imagenOriginal.getWidth());
        short[][] nuevaMatrizR = new short[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        short[][] nuevaMatrizG = new short[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        short[][] nuevaMatrizB = new short[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        
        Color colorAuxiliar;
        for(int i = 0; i < imagenOriginal.getWidth(); i++){
            for(int j = 0; j < imagenOriginal.getHeight(); j++){
                colorAuxiliar = new Color(imagenOriginal.getRGB(i, j));
                nuevaMatrizR[i][j] = (short) colorAuxiliar.getRed();
                nuevaMatrizG[i][j] = (short) colorAuxiliar.getGreen();
                nuevaMatrizB[i][j] = (short) colorAuxiliar.getBlue();
            }
        }
        setMatrizR(nuevaMatrizR);
        setMatrizG(nuevaMatrizG);
        setMatrizB(nuevaMatrizB);     
    }
    
    private void cargarGris(short[][] matrizRojo, short[][] matrizVerde, short[][] matrizAzul) {
        short[][] nuevaMatrizGris = new short[matrizRojo.length][matrizRojo[0].length];
        for (int i = 0; i < nuevaMatrizGris.length; i++) {
            for (int j = 0; j < nuevaMatrizGris[0].length; j++) {
                short escalaDeGrises = (short) ((0.299 * matrizRojo[i][j]) + (0.587 * matrizVerde[i][j]) + (0.114 * matrizAzul[i][j]));
                nuevaMatrizGris[i][j] = escalaDeGrises;
            }
        }
        setMatrizGris(nuevaMatrizGris);
    }

    
    public BufferedImage convierteMatrizEnBuffered(short[][] matrizGris){
    short pixelNuevo;
    int pixelSRGB;
    int ancho = matrizGris.length; 
    int alto = matrizGris[0].length; 
    BufferedImage bufferImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
    
    for(int i = 0; i < ancho; i++){
        for(int j = 0; j < alto; j++){
            pixelNuevo = matrizGris[i][j];
            pixelSRGB = pixelNuevo << 16 | pixelNuevo << 8 | pixelNuevo;
            bufferImagen.setRGB(i, j, pixelSRGB);
        }
    }
    
    return bufferImagen;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public short[][] getMatrizGris() {
        return matrizGris;
    }

    public void setMatrizGris(short[][] matrizGris) {
        this.matrizGris = matrizGris;
    }

    public short[][] getMatrizR() {
        return matrizR;
    }

    public void setMatrizR(short[][] matrizR) {
        this.matrizR = matrizR;
    }

    public short[][] getMatrizG() {
        return matrizG;
    }

    public void setMatrizG(short[][] matrizG) {
        this.matrizG = matrizG;
    }

    public short[][] getMatrizB() {
        return matrizB;
    }

    public void setMatrizB(short[][] matrizB) {
        this.matrizB = matrizB;
    }
    
    public short[][][] getRGB() {
    short[][][] matrizRGB = new short[filas][columnas][3];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            matrizRGB[i][j][0] = matrizR[i][j];
            matrizRGB[i][j][1] = matrizG[i][j];
            matrizRGB[i][j][2] = matrizB[i][j];
        }
    }

    return matrizRGB;
    }


    public BufferedImage getBufferImagen() {
        return bufferImagen;
    }

    public void setBufferImagen(BufferedImage bufferImagen) {
        this.bufferImagen = bufferImagen;
    }

    public File getArchivoImagen() {
        return archivoImagen;
    }

    public void setArchivoImagen(File archivoImagen) {
        this.archivoImagen = archivoImagen;
    }
    
    @Override
    public Imagen clone(){
        Imagen copia = null;
        try {
        copia = (Imagen) super.clone();

        // Clonar las matrices
        copia.matrizGris = matrizGris.clone();
        copia.matrizR = matrizR.clone();
        copia.matrizG = matrizG.clone();
        copia.matrizB = matrizB.clone();

        // Clonar el BufferedImage
        copia.bufferImagen = new BufferedImage(bufferImagen.getWidth(), bufferImagen.getHeight(), bufferImagen.getType());
        for (int i = 0; i < bufferImagen.getWidth(); i++) {
            for (int j = 0; j < bufferImagen.getHeight(); j++) {
                copia.bufferImagen.setRGB(i, j, bufferImagen.getRGB(i, j));
            }
        }

    } catch (CloneNotSupportedException ex) {
        Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
    }
    return copia;
    }
    private String obtenerFormato(File archivo) {
        String nombreArchivo = archivo.getName();
        int puntoIndex = nombreArchivo.lastIndexOf(".");
            if (puntoIndex != -1) {
                return nombreArchivo.substring(puntoIndex + 1);
            }
        return "";
    }
}
