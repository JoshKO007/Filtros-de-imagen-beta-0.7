package modelo;

import java.util.Arrays;

public class Ruido {
    private Imagen objImagen;
    
    public Ruido(){
    }
    
    public Ruido(Imagen objImagen){
        this.objImagen = objImagen;
    }
    
public short[][] filtroMediaAritmetica(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizMA = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            int suma = 0;
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (k != 0 || l != 0) { 
                        suma += matriz[i + k][j + l];
                    }
                }
            }
            short resultado = (short) (suma / 8);
            matrizMA[i][j] = resultado;
        }
    }
    return matrizMA;
}
public short[][] filtroMediaGeometrica(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizMA = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            double multiplicacion = 1.0;
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (k != 0 || l != 0) { 
                        multiplicacion *= matriz[i + k][j + l];
                    }
                }
            }
            double resultado = Math.pow(multiplicacion, 1.0 / 8.0);
            matrizMA[i][j] = (short) resultado;
        }
    }
    return matrizMA;
}
    public short[][] filtroContraArmonica(short[][] matriz, double Q) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizCA = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            double sumaQp = 0.0;
            double sumaQn = 0.0;

            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (k != 0 || l != 0) { 
                        double valor = matriz[i + k][j + l];
                        sumaQp += Math.pow(valor, Q + 1);
                        sumaQn += Math.pow(valor, Q);
                    }
                }
            }
            matrizCA[i][j] = (short) (sumaQp / sumaQn);
        }
    }
    return matrizCA;
    }
public short[][] filtroMediana(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizResultado = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            short[] valores = new short[8];
            valores[0] = matriz[i - 1][j - 1];
            valores[1] = matriz[i - 1][j];
            valores[2] = matriz[i - 1][j + 1];
            valores[3] = matriz[i][j - 1];
            valores[4] = matriz[i][j + 1];
            valores[5] = matriz[i + 1][j - 1];
            valores[6] = matriz[i + 1][j];
            valores[7] = matriz[i + 1][j + 1];
            
            Arrays.sort(valores);
            
            short mediana = (short) ((valores[3] + valores[4]) / 2);
            matrizResultado[i][j] = mediana;
        }
    }
    return matrizResultado;
}

public short[][] filtroMaximo(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizResultado = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            short[] valores = new short[8];
            valores[0] = matriz[i - 1][j - 1];
            valores[1] = matriz[i - 1][j];
            valores[2] = matriz[i - 1][j + 1];
            valores[3] = matriz[i][j - 1];
            valores[4] = matriz[i][j + 1];
            valores[5] = matriz[i + 1][j - 1];
            valores[6] = matriz[i + 1][j];
            valores[7] = matriz[i + 1][j + 1];
            
            Arrays.sort(valores);
            
            short mediana = (short) ((valores[7]));
            matrizResultado[i][j] = mediana;           
        }
    }
    return matrizResultado;
    }
    public short[][] filtroMinimo(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizResultado = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            short[] valores = new short[8];
            valores[0] = matriz[i - 1][j - 1];
            valores[1] = matriz[i - 1][j];
            valores[2] = matriz[i - 1][j + 1];
            valores[3] = matriz[i][j - 1];
            valores[4] = matriz[i][j + 1];
            valores[5] = matriz[i + 1][j - 1];
            valores[6] = matriz[i + 1][j];
            valores[7] = matriz[i + 1][j + 1];
            
            Arrays.sort(valores);
            short mediana = (short) ((valores[0]));
            matrizResultado[i][j] = mediana;           
        }
    }
    return matrizResultado;
    }
    
        public short[][] filtroMedio(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizResultado = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            short[] valores = new short[8];
            valores[0] = matriz[i - 1][j - 1];
            valores[1] = matriz[i - 1][j];
            valores[2] = matriz[i - 1][j + 1];
            valores[3] = matriz[i][j - 1];
            valores[4] = matriz[i][j + 1];
            valores[5] = matriz[i + 1][j - 1];
            valores[6] = matriz[i + 1][j];
            valores[7] = matriz[i + 1][j + 1];
            
            Arrays.sort(valores);
            
            short mediana = (short) ((valores[7]+valores[0])/2);
            matrizResultado[i][j] = mediana;           
        }
    }
    return matrizResultado;
    }
    
public short[][] filtroMediaAdaptativa(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizMA = new short[filas][columnas];

    for (int i = 1; i < filas - 1; i++) {
        for (int j = 1; j < columnas - 1; j++) {
            short[] valores = new short[9];
            valores[0] = matriz[i - 1][j - 1];
            valores[1] = matriz[i - 1][j];
            valores[2] = matriz[i - 1][j + 1];
            valores[3] = matriz[i][j - 1];
            valores[4] = matriz[i][j];
            valores[5] = matriz[i][j + 1];
            valores[6] = matriz[i + 1][j - 1];
            valores[7] = matriz[i + 1][j];
            valores[8] = matriz[i + 1][j + 1];

            int w = 3;
            int wmax = 7;
            Arrays.sort(valores);
                int zmin = valores[0];
                int zmax = valores[w * w - 1];
            // Calcular la mediana
            short zmed = (short) valores[w * w / 2];

            // Nivel A
            if (w <= wmax) {
                if (zmin < zmed && zmed < zmax) {
                    // Nivel B
                    if (zmin < matriz[i][j] && matriz[i][j] < zmax) {
                        // Retornar el valor del píxel
                        matrizMA[i][j] = matriz[i][j];
                    } else {
                        // Retornar la mediana
                        matrizMA[i][j] = zmed;
                    }
                } else {
                    // Nivel A
                    w++;
                }
   
            }else matrizMA[i][j] = zmed;

        }
    }
    return matrizMA;
}

}
