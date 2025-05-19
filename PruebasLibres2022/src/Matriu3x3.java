 import java.util.Random;

public class Matriu3x3 {
    // Genera una matriu 3×3 amb valors aleatoris entre 0 i 100
    public static int[][] generarMatriu(int files, int cols) {
        Random rnd = new Random();
        int[][] m = new int[files][cols];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = rnd.nextInt(101);  // 0..100
            }
        }
        return m;
    }

    // Imprimeix la matriu per pantalla
    public static void imprimirMatriu(int[][] m) {
        for (int[] fila : m) {
            System.out.print("[ ");
            for (int val : fila) {
                System.out.printf("%3d ", val);
            }
            System.out.println("]");
        }
    }

    // Retorna la transposada de la matriu donada
    public static int[][] transposar(int[][] m) {
        int files = m.length;
        int cols = m[0].length;
        int[][] t = new int[cols][files];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                t[j][i] = m[i][j];
            }
        }
        return t;
    }

    public static void main(String[] args) {
        // 1. Generar la matriu original 3×3
        int[][] matriu = generarMatriu(3, 3);

        // 2. Mostrar-la
        System.out.println("Matriu original:");
        imprimirMatriu(matriu);

        // 3. Calcular la transposada
        int[][] transposada = transposar(matriu);

        // 4. Mostrar la transposada
        System.out.println("\nMatriu transposada:");
        imprimirMatriu(transposada);
    }
}
// Comparativa de la matriu original i la transposada   
// Original:
// [  12   34   56 ]
// [  78   90   12 ]
// [  34   56   78 ]
// Transposada:
// [  12   78   34 ]
// [  34   90   56 ]
// [  56   12   78 ]
