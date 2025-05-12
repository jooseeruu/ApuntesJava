import java.util.*;

/**
 * Aplicación de consola que gestiona una clasificación deportiva con jugadores.
 *
 * Funcionalidades base:
 * 1. newTeam()       – Añade un equipo (0 pts)
 * 2. updateTeam()    – Modifica la puntuación de un equipo
 * 3. deleteTeam()    – Elimina un equipo
 * 4. updateLeague()  – Ordena la clasificación de mayor a menor
 * 5. viewLeague()    – Muestra la clasificación (puntos + goles)
 * --- Ampliaciones solicitadas ---
 * 6. newPlayer()     – Añade un jugador a un equipo
 * 7. totalLeagueGoals() – Suma de goles de toda la liga
 */
public class LeagueManager {

    /* =================== MODELO =================== */

    /** Jugador con nombre y goles. */
    private static class Player {
        String name;
        int goals;

        Player(String name, int goals) {
            this.name  = name;
            this.goals = goals;
        }
    }

    /** Equipo con nombre, puntos y plantilla de jugadores. */
    private static class Team {
        String name;
        int points;
        final List<Player> players = new ArrayList<>();

        Team(String name) {
            this.name   = name;
            this.points = 0; // puntuación inicial
        }

        /** Goles totales marcados por todos sus jugadores. */
        int totalGoals() {
            return players.stream().mapToInt(p -> p.goals).sum();
        }
    }

    /* =================== DATOS Y ENTRADA =================== */
    private final List<Team> league = new ArrayList<>();
    private final Scanner   scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new LeagueManager().run();
    }

    /* =================== MENÚ PRINCIPAL =================== */
    private void run() {
        int option;
        do {
            printMenu();
            option = readInt("Seleccione una opción: ");
            switch (option) {
                case 1 -> newTeam();
                case 2 -> updateTeam();
                case 3 -> deleteTeam();
                case 4 -> updateLeague();
                case 5 -> viewLeague();
                case 6 -> newPlayer();
                case 7 -> totalLeagueGoals();
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Inténtelo de nuevo.\n");
            }
        } while (option != 0);
    }

    private void printMenu() {
        System.out.println("""
                ------------------------------
                MENÚ PRINCIPAL
                1. Añadir equipo
                2. Modificar puntuación de equipo
                3. Eliminar equipo
                4. Actualizar clasificación (ordenar)
                5. Ver clasificación
                6. Añadir jugador a un equipo
                7. Ver total de goles de la liga
                0. Salir
                ------------------------------""");
    }

    /* =================== FUNCIONES OBLIGATORIAS =================== */
    // 1. Añadir equipo
    private void newTeam() {
        System.out.print("Nombre del nuevo equipo: ");
        String name = scanner.nextLine().trim();
        league.add(new Team(name));
        System.out.println("Equipo \"" + name + "\" añadido con 0 puntos.\n");
    }

    // 2. Modificar puntuación
    private void updateTeam() {
        if (league.isEmpty()) {
            System.out.println("No hay equipos en la clasificación.\n");
            return;
        }
        viewLeague();
        int pos = readInt("Posición del equipo a actualizar: ") - 1;
        if (!isValidPosition(pos)) return;

        int newPoints = readInt("Nueva puntuación: ");
        league.get(pos).points = newPoints;
        System.out.println("Puntuación actualizada.\n");
    }

    // 3. Eliminar equipo
    private void deleteTeam() {
        if (league.isEmpty()) {
            System.out.println("No hay equipos en la clasificación.\n");
            return;
        }
        viewLeague();
        int pos = readInt("Posición del equipo a eliminar: ") - 1;
        if (!isValidPosition(pos)) return;

        Team removed = league.remove(pos);
        System.out.println("Equipo \"" + removed.name + "\" eliminado.\n");
    }

    // 4. Ordenar clasificación
    private void updateLeague() {
        league.sort((a, b) -> Integer.compare(b.points, a.points));
        System.out.println("Clasificación ordenada de mayor a menor.\n");
    }

    // 5. Ver clasificación (puntos + goles)
    private void viewLeague() {
        updateLeague(); // siempre ordenada
        System.out.println("===== CLASIFICACIÓN =====");
        if (league.isEmpty()) {
            System.out.println("Sin equipos.\n");
            return;
        }
        int rank = 1;
        for (Team t : league) {
            System.out.printf("%d) %-20s %3d pts | %3d goles%n", rank++, t.name, t.points, t.totalGoals());
        }
        System.out.println();
    }

    /* =================== NUEVAS FUNCIONES =================== */
    // 6. Añadir jugador a un equipo
    private void newPlayer() {
        if (league.isEmpty()) {
            System.out.println("No hay equipos en la clasificación.\n");
            return;
        }
        viewLeague();
        int pos = readInt("Posición del equipo: ") - 1;
        if (!isValidPosition(pos)) return;

        System.out.print("Nombre del jugador: ");
        String playerName = scanner.nextLine().trim();
        int goals = readInt("Goles anotados por " + playerName + ": ");

        Team team = league.get(pos);
        team.players.add(new Player(playerName, goals));
        System.out.println("Jugador añadido a \"" + team.name + "\".\n");
    }

    // 7. Suma total de goles en la liga
    private void totalLeagueGoals() {
        int totalGoals = league.stream().mapToInt(Team::totalGoals).sum();
        System.out.println("Goles totales en la liga: " + totalGoals + "\n");
    }

    /* =================== UTILIDADES =================== */
    private boolean isValidPosition(int pos) {
        if (pos < 0 || pos >= league.size()) {
            System.out.println("Posición no válida.\n");
            return false;
        }
        return true;
    }

    /** Lee un entero mostrando un prompt. */
    private int readInt(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine().trim();
        return Integer.parseInt(line); // Se asume entero válido
    }
}
