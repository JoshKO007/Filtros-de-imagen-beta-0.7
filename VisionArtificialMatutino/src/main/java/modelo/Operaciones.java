package modelo;

public class Operaciones {
    private Imagen objImagen;
    
    public Operaciones(){
    }
    
    public Operaciones(Imagen objImagen){
        this.objImagen = objImagen;
    }
    
    public short[][] rotar90Grados(short[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        short[][] matrizR90 = new short[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizR90[j][filas - 1 - i] = matriz[i][j];
            }
        }
        return matrizR90;
    }
    
    public short[][] rotar180Grados(short[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        short[][] matrizR180 = new short[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizR180[filas - 1 - i][columnas - 1 - j] = matriz[i][j];
            }
        }
        return matrizR180;
    }
    
    public short[][] rotar270Grados(short[][] matriz){
        int filas = matriz.length;
        int columnas = matriz[0].length;
        short[][] matrizR270 = new short[columnas][filas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizR270[columnas - 1 - j][i] = matriz[i][j];
            }
        }
        return matrizR270;
    }
    
    public short[][] trasladar(short[][] matriz, int desplazamiento) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizTraslacion = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int nuevoX = i - desplazamiento;
            int nuevoY = j - desplazamiento;
            if (nuevoX >= 0 && nuevoX < filas && nuevoY >= 0 && nuevoY < columnas) {
                matrizTraslacion[i][j] = matriz[nuevoX][nuevoY];
            } else {
                matrizTraslacion[i][j] = 0;
            }
        }
    }
    return matrizTraslacion;
    }
    
    public short[][] SumarEscalar(short[][] matriz, int brillo){
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizSumaEscalar = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int nuevoValor = matriz[i][j] + brillo;
            nuevoValor = Math.min(255, Math.max(0, nuevoValor));
            matrizSumaEscalar[i][j] = (short)nuevoValor;
        }
    }
    return matrizSumaEscalar;
    }
    
    public short[][] RestaEscalar(short[][] matriz, int oscuro){
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizSumaEscalar = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            int nuevoValor = matriz[i][j] - oscuro;
            nuevoValor = Math.min(255, Math.max(0, nuevoValor));
            matrizSumaEscalar[i][j] = (short)nuevoValor;
        }
    }
    return matrizSumaEscalar;
    }    
    
    public short[][] suma(short[][] matrizA, short[][] matrizB) {
    int filasA = matrizA.length;
    int columnasA = matrizA[0].length;
    int filasB = matrizB.length;
    int columnasB = matrizB[0].length;

    if (filasA != filasB || columnasA != columnasB) {
        throw new IllegalArgumentException("Las imágenes deben tener el mismo tamaño para realizar la superposición.");
    }

    short[][] matrizSuma = new short[filasA][columnasA];

    for (int i = 0; i < filasA; i++) {
        for (int j = 0; j < columnasA; j++) {
            int valorA = matrizA[i][j];
            int valorB = matrizB[i][j];
            int resultado = (valorA + valorB) / 2; 
            resultado = Math.min(255, Math.max(0, resultado)); 
            matrizSuma[i][j] = (short) resultado;
        }
    }
        return matrizSuma;
    }


    
    public short[][] resta(short[][] matrizA, short[][] matrizB) {
    int filasA = matrizA.length;
    int columnasA = matrizA[0].length;
    int filasB = matrizB.length;
    int columnasB = matrizB[0].length;

    if (filasA != filasB || columnasA != columnasB) {
        throw new IllegalArgumentException("Las imágenes deben tener el mismo tamaño para realizar la superposición.");
    }

    short[][] matrizResta = new short[filasA][columnasA];

    for (int i = 0; i < filasA; i++) {
        for (int j = 0; j < columnasA; j++) {
            int valorA = matrizA[i][j];
            int valorB = matrizB[i][j];
            int resultado = Math.abs(valorA - valorB); 
            resultado = Math.min(255, Math.max(0, resultado)); 
            matrizResta[i][j] = (short) resultado;
        }
    }
        return matrizResta;
    }
    
    public short[][] reflejarEnY(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizReflejada = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (i < filas / 2) {
                matrizReflejada[i][j] = matriz[i][j];
            } else {
                matrizReflejada[i][j] = matriz[filas - 1 - i][j];
            }
        }
    }
    return matrizReflejada;
    }
    public short[][] reflejarEnX(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] matrizReflejada = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (j < columnas / 2) {
                matrizReflejada[i][j] = matriz[i][j];
            } else {
                matrizReflejada[i][j] = matriz[i][columnas - 1 - j];
            }
        }
    }
    return matrizReflejada;
    }
    
    public short[][] AND(short[][] matriz, short[][] matriz2) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] resultado = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            resultado[i][j] = (short) (matriz[i][j] & matriz2[i][j]);
        }
    }
        return resultado;
    }
    
    public short[][] OR(short[][] matriz, short[][] matriz2) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] resultado = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            resultado[i][j] = (short) (matriz[i][j] | matriz2[i][j]);
        }
    }
        return resultado;
    }
    
    public short[][] NOT(short[][] matriz) {
    int filas = matriz.length;
    int columnas = matriz[0].length;
    short[][] resultado = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (matriz[i][j] == 0) {
                resultado[i][j] = 255;
            } else {
                resultado[i][j] = 0;
            }
        }
    }
        return resultado;
    }
    
    public short[][] XOR(short[][] matriz1, short[][] matriz2) {
    int filas = matriz1.length;
    int columnas = matriz1[0].length;
    short[][] resultado = new short[filas][columnas];

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            resultado[i][j] = (short) (matriz1[i][j] ^ matriz2[i][j]); 
        }
    }

    return resultado;
}

}
    

