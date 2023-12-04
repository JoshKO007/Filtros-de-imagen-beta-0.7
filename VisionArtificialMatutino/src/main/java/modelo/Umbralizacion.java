package modelo;

public class Umbralizacion {
    private short umbral1;
    private short umbral2;
    
    public Umbralizacion(){
    }
    
    public Umbralizacion(short u){
        this.umbral1 = u;
    }
    public Umbralizacion(short u1, short u2){
        this.umbral1 = u1;
        this.umbral2 = u2;
    }
    
    public short[][] inverso(short[][] matrizOriginal){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizModificada[i][j] = (short) (255 - matrizOriginal[i][j]);
            }
        }
        return matrizModificada;
    }
    
    public short[][] umbral(short[][] matrizOriginal, int u1){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(matrizOriginal[i][j] > u1){
                    matrizModificada[i][j] = 0;
                }else{
                    matrizModificada[i][j] = 255;
                }
            }
        }
        return matrizModificada;
    }
    
    public short[][] umbralin(short[][] matrizOriginal, int u1){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(matrizOriginal[i][j] > u1){
                    matrizModificada[i][j] = 255;
                }else{
                    matrizModificada[i][j] = 0;
                }
            }
        }
        return matrizModificada;
    }
    public short[][] intervaloUmbral001(short[][] matrizOriginal, int u1, int u2){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizOriginal[i][j] < u1 && matrizOriginal[i][j] < u2) {
                    matrizModificada[i][j] = 255; 
                } else if(matrizOriginal[i][j] > u1 || matrizOriginal[i][j] > u2){
                    matrizModificada[i][j] = 0; 
                }
            }   
        }
        return matrizModificada;
    }
    public short[][] intervaloUmbralin001(short[][] matrizOriginal, int u1, int u2){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizOriginal[i][j] > u1 && matrizOriginal[i][j] > u2) {
                    matrizModificada[i][j] = 255; 
                } else if(matrizOriginal[i][j] < u1 || matrizOriginal[i][j] < u2){
                    matrizModificada[i][j] = 0; 
                }
            }   
        }
        return matrizModificada;
    }
    public short[][] intervaloUmbralGris(short[][] matrizOriginal, int u1, int u2){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizOriginal[i][j] < u1 && matrizOriginal[i][j] < u2) {
                    matrizModificada[i][j] = matrizOriginal[i][j]; 
                } else if(matrizOriginal[i][j] > u1 || matrizOriginal[i][j] > u2){
                    matrizModificada[i][j] = 255; 
                }
            }   
        }
        return matrizModificada;
    }
        public short[][] intervaloUmbralGrisIn(short[][] matrizOriginal, int u1, int u2){
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        short[][] matrizModificada = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizOriginal[i][j] > u1 && matrizOriginal[i][j] > u2) {
                    matrizModificada[i][j] = matrizOriginal[i][j]; 
                } else if(matrizOriginal[i][j] < u1 || matrizOriginal[i][j] < u2){
                    matrizModificada[i][j] = 255; 
                }
            }   
        }
        return matrizModificada;
    }
}
