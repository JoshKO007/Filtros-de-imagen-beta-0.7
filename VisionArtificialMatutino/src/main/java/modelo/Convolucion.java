/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
/**
 *
 * @author joshtin_mac
 */
public class Convolucion {
        private short conv1;
        private short conv2;
        private short conv3;
        private short conv4;
        private short conv5;
        private short conv6;
        private short conv7;
        private short conv8;
        private short conv9;        
    
    public Convolucion(){
    }
    
    public Convolucion(short u1, short u2, short u3, short u4, short u5, short u6, short u7, short u8, short u9){
        this.conv1= u1;
        this.conv2 = u2;
        this.conv3= u3;
        this.conv4 = u4;
        this.conv5= u5;
        this.conv6 = u6;
        this.conv7= u7;
        this.conv8 = u8;
        this.conv8 = u9;  

    }
    
    
public short[][] convolucion(short[][] matrizOriginal, short u1, short u2, short u3, short u4, short u5, short u6, short u7, short u8, short u9) {
    short[][] kernel = {
        {u1, u2, u3},
        {u4, u5, u6},
        {u7, u8, u9}
    };
    
    int filas = matrizOriginal.length;
    int columnas = matrizOriginal[0].length;
    int kernelFilas = kernel.length;
    int kernelColumnas = kernel[0].length;
    short[][] matrizModificada = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int suma = 0;
            for (int x = 0; x < kernelFilas; x++) {
                for (int y = 0; y < kernelColumnas; y++) {
                    int fila = i + x;
                    int columna = j + y;
                    if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
                        suma += matrizOriginal[fila][columna] * kernel[x][y];
                    }
                }
            }
            matrizModificada[i][j] = (short) suma;
        }
    }

    // Normalización de la matriz de salida
    int min = Short.MAX_VALUE;
    int max = Short.MIN_VALUE;
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            min = Math.min(min, matrizModificada[i][j]);
            max = Math.max(max, matrizModificada[i][j]);
        }
    }

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int valorNormalizado = (int) (255.0 * (matrizModificada[i][j] - min) / (max - min));
            matrizModificada[i][j] = (short) valorNormalizado;
        }
    }

    return matrizModificada;
}
}