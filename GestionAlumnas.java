import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada del programa y menú de gestión de alumnas.
 */
public class GestionAlumnas {

    private final Scanner sc = new Scanner(System.in);
    private final List<Alumno> listaAlumnas = new ArrayList<>();

    /*---------------------------- Menú -------------------------------*/
    private void mostrarMenu() {
        System.out.println("\n--- Gestión de Alumnas ---");
        System.out.println("1. Añadir alumna");
        System.out.println("2. Listar alumnas");
        System.out.println("3. Añadir nota");
        System.out.println("4. Eliminar nota");
        System.out.println("5. Mostrar detalles de una alumna");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void ejecutar() {
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1" -> opcionAnadirAlumna();
                case "2" -> opcionListarAlumnas();
                case "3" -> opcionAnadirNota();
                case "4" -> opcionEliminarNota();
                case "5" -> opcionMostrarDetalles();
                case "6" -> salir = true;
                default  -> System.out.println("Opción no válida.");
            }
        }
        System.out.println("¡Hasta luego!");
    }

    /*---------------------- Opciones de menú ------------------------*/
    private void opcionAnadirAlumna() {
        System.out.print("Nombre de la alumna: ");
        String nombre = sc.nextLine();
        try {
            String msg = Alumno.anadirAlumno(listaAlumnas, nombre);
            System.out.println(msg);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void opcionListarAlumnas() {
        if (listaAlumnas.isEmpty()) {
            System.out.println("No hay alumnas registradas.");
        } else {
            System.out.println("Listado de alumnas:");
            listaAlumnas.forEach(a ->
                System.out.printf("  ID: %d | Nombre: %s%n", a.getId(), a.getNombre())
            );
        }
    }

    private Alumno obtenerAlumnaPorId(int id) {
        return listaAlumnas.stream()
                           .filter(a -> a.getId() == id)
                           .findFirst()
                           .orElse(null);
    }

    private void opcionAnadirNota() {
        try {
            System.out.print("ID de la alumna: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Alumno a = obtenerAlumnaPorId(id);

            if (a == null) {
                System.out.println("ID no encontrado.");
                return;
            }
            System.out.print("Nota a añadir (0-10): ");
            double nota = Double.parseDouble(sc.nextLine().trim());
            a.anadirNota(nota);
            System.out.println("Nota añadida.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void opcionEliminarNota() {
        try {
            System.out.print("ID de la alumna: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Alumno a = obtenerAlumnaPorId(id);

            if (a == null) {
                System.out.println("ID no encontrado.");
                return;
            }
            if (a.getNotas().isEmpty()) {
                System.out.println("La alumna no tiene notas.");
                return;
            }

            System.out.println("Notas actuales:");
            for (int i = 0; i < a.getNotas().size(); i++) {
                System.out.printf("  %d: %.1f%n", i, a.getNotas().get(i));
            }
            System.out.print("Posición de la nota a eliminar: ");
            int pos = Integer.parseInt(sc.nextLine().trim());
            a.eliminarNota(pos);
            System.out.println("Nota eliminada.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void opcionMostrarDetalles() {
        try {
            System.out.print("ID de la alumna: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Alumno a = obtenerAlumnaPorId(id);

            if (a == null) {
                System.out.println("ID no encontrado.");
            } else {
                System.out.println(a);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        }
    }

    /*--------------------------- main() ------------------------------*/
    public static void main(String[] args) {
        new GestionAlumnas().ejecutar();
    }
}
