import java.util.*;

/**
 * Aplicación de consola que gestiona una clasificación deportiva.
 * 
 * Funcionalidades exigidas:
 * 1. newTeam()       – Añade un equipo (0 pts)                       (1 punto)
 * 2. updateTeam()    – Modifica la puntuación de un equipo          (2 puntos)
 * 3. deleteTeam()    – Elimina un equipo                            (3 puntos)
 * 4. updateLeague()  – Ordena la clasificación de mayor a menor     (4 puntos)
 * 5. viewLeague()    – Muestra la clasificación                     (2 puntos)
 * 
 * Cada llamada desde el menú interactivo garantiza que la entrada es un entero, salvo
 * la posición, cuya validez sí se comprueba.
 */
public class Original {

    /**
     * Clase interna que representa a un equipo con nombre y puntuación.
     */
    private static class Team {
        String name;
        int points;

        Team(String name) {
            this.name = name;
            this.points = 0; // puntuación inicial
        }
    }

    /* Lista que contiene la clasificación */
    private final List<Team> league = new ArrayList<>();

    /* Scanner único para toda la aplicación */
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new Original().run();
    }

    /**
     * Bucle principal del menú interactivo.
     */
    private void run() {
        int option;
        do {
            printMenu();
            option = Integer.parseInt(scanner.nextLine().trim());
            switch (option) {
                case 1 -> newTeam();
                case 2 -> updateTeam();
                case 3 -> deleteTeam();
                case 4 -> updateLeague();
                case 5 -> viewLeague();
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (option != 0);
    }

    /** Imprime el menú principal */
    private void printMenu() {
        System.out.println("""
                ------------------------------
                MENÚ PRINCIPAL
                1. Añadir equipo
                2. Modificar puntuación
                3. Eliminar equipo
                4. Actualizar clasificación
                5. Ver clasificación
                0. Salir
                ------------------------------""");
        System.out.print("Seleccione una opción: ");
    }

    // 1 punto CProf 3
    private void newTeam() {
        System.out.print("Nombre del nuevo equipo: ");
        String name = scanner.nextLine().trim();
        league.add(new Team(name));
        System.out.println("Equipo \"" + name + "\" añadido con 0 puntos.\n");
    }

    // 2 puntos CProf 3
    private void updateTeam() {
        if (league.isEmpty()) {
            System.out.println("No hay equipos en la clasificación.\n");
            return;
        }
        viewLeague();
        System.out.print("Posición del equipo a actualizar: ");
        int pos = Integer.parseInt(scanner.nextLine().trim()) - 1; // se muestra desde 1
        if (!isValidPosition(pos)) return;

        System.out.print("Nueva puntuación: ");
        int newPoints = Integer.parseInt(scanner.nextLine().trim());
        league.get(pos).points = newPoints;
        System.out.println("Puntuación actualizada.\n");
    }

    // 3 puntos CProf 3
    private void deleteTeam() {
        if (league.isEmpty()) {
            System.out.println("No hay equipos en la clasificación.\n");
            return;
        }
        viewLeague();
        System.out.print("Posición del equipo a eliminar: ");
        int pos = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (!isValidPosition(pos)) return;

        Team removed = league.remove(pos);
        System.out.println("Equipo \"" + removed.name + "\" eliminado.\n");
    }

    // 4 puntos CProf 3
    private void updateLeague() {
        league.sort((a, b) -> Integer.compare(b.points, a.points));
        System.out.println("Clasificación ordenada de mayor a menor.\n");
    }

    // 2 puntos CProf 2
    private void viewLeague() {
        updateLeague(); // Asegura que siempre se muestre ordenada
        System.out.println("===== CLASIFICACIÓN =====");
        if (league.isEmpty()) {
            System.out.println("Sin equipos.\n");
            return;
        }
        int rank = 1;
        for (Team t : league) {
            System.out.printf("%d) %-20s %d pts%n", rank++, t.name, t.points);
        }
        System.out.println();
    }

    /**
     * Comprueba que la posición introducida por el usuario es válida.
     */
    private boolean isValidPosition(int pos) {
        if (pos < 0 || pos >= league.size()) {
            System.out.println("Posición no válida.\n");
            return false;
        }
        return true;
    }
}
