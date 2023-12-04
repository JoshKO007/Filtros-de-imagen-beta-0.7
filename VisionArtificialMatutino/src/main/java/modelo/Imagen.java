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
    
    public void cargarGris(short[][] matrizRojo, short[][] matrizVerde, short[][] matrizAzul) {
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
    
    public BufferedImage RGBtoCMY(short[][] R, short[][] G, short[][] B){
        int height = R[0].length;
        int width = R.length;
        
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int C = 255 - R[x][y];
                int M = 255 - G[x][y];
                int Y = 255 - B[x][y];
                
                int rgb = new Color(C,M,Y).getRGB();
                imagen.setRGB(x, y, rgb);
            }
        }
        return imagen;
    }
    
    public BufferedImage CMYtoRGB(short[][] C, short[][] M, short[][] Y){
        int height = C[0].length;
        int width = C.length;
        
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int R = 255 - C[x][y];
                int G = 255 - M[x][y];
                int B = 255 - Y[x][y];
                
                int rgb = new Color(R,G,B).getRGB();
                imagen.setRGB(x, y, rgb);
            }
        }
        return imagen;
    }
    
        public BufferedImage RGBtoHSI(short[][] R, short[][] G, short[][] B){
        int height = G[0].length;
        int width = G.length;
        
        BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                double numer = 0.5 * ((R[x][y] - G[x][y]) + (R[x][y] - B[x][y]));
                double deno = Math.pow(2, R[x][y] + G[x][y]);
                double otro = (R[x][y] - B[x][y]) * (G[x][y] - B[x][y]);
                deno = deno + otro;
                
                double H = Math.acos(Math.max(-1, Math.min(1, numer/deno)));
                int h = (int) Math.toDegrees(H);
                
                double min = Math.min(R[x][y], Math.min(G[x][y], B[x][y]));
                double suma = R[x][y] + G[x][y] + B[x][y];
                double S = 1 - (3 * min/suma);
                int  s = (int) (S * 100);
                
                double I = (R[x][y] + G[x][y] + B[x][y]) /3;
                int i = (int) I;
                
                h = (int) Math.max(0, Math.min(255, h));
                s = (int) Math.max(0, Math.min(255, s));
                i = (int) Math.max(0, Math.min(255, i));
                
                int HSI = new Color(h,s,i).getRGB();
                imagen.setRGB(x, y, HSI);
            }
        }
        return imagen;
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
