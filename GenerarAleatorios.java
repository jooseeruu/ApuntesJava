import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerarAleatorios {
    public static void main(String[] args) {
        Random rand = new Random();               // Generador de aleatorios
        List<Integer> lista = new ArrayList<>();  // ArrayList para almacenar los números

        // Generamos 10 números aleatorios y los añadimos al ArrayList
        for (int i = 0; i < 10; i++) {
            int num = rand.nextInt(100);         // entero aleatorio entre 0 y 99
            lista.add(num);                      // "empujamos" el número al ArrayList
        }

        // Imprimimos el contenido de la lista
        System.out.println("Números generados: " + lista);
    }
}
