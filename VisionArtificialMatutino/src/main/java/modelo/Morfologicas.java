/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author josho
 */

public class Morfologicas {
    
    private Imagen objImagen;
    
    public Morfologicas(){
    }
    
    public Morfologicas(Imagen objImagen){
        this.objImagen = objImagen;
    }

    
    
    public short[][] dilatacion(short[][] matriz, short[][] elementoEstructurante) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int n = elementoEstructurante.length;
        int m = elementoEstructurante[0].length;

        short[][] resultado = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                short maxValor = Short.MAX_VALUE;

                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < m; l++) {
                        if (elementoEstructurante[k][l] == 1) {
                            int x = i - n / 2 + k;
                            int y = j - m / 2 + l;

                            if (x >= 0 && x < filas && y >= 0 && y < columnas) {
                                maxValor = (short) Math.min(maxValor, matriz[x][y]);
                            }
                        }
                    }
                }

                resultado[i][j] = maxValor;
            }
        }

        return resultado;
    }
    
    public short[][] erosion(short[][] matriz, short[][] elementoEstructurante) {
        int filas = matriz.length;
        int columnas = matriz[0].length;
        int n = elementoEstructurante.length;
        int m = elementoEstructurante[0].length;

        short[][] resultado = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                short minValor = Short.MIN_VALUE;

                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < m; l++) {
                        if (elementoEstructurante[k][l] == 1) {
                            int x = i - n / 2 + k;
                            int y = j - m / 2 + l;

                            if (x >= 0 && x < filas && y >= 0 && y < columnas) {
                                minValor = (short) Math.max(minValor, matriz[x][y]);
                            }
                        }
                    }
                }

                resultado[i][j] = minValor;
            }
        }

        return resultado;
    }


}
