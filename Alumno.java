import java.util.ArrayList;
import java.util.List;

/**
 * Representa a una alumna con ID autoincremental, nombre y lista de notas.
 */
public class Alumno {

    /*--------------------------- Atributos ---------------------------*/
    private static int idCounter = 1;          // variable estática (ids únicos)

    private final int id;                      // id único de la alumna
    private String nombre;                     // nombre de la alumna
    private final List<Double> notas;          // lista de notas

    /*------------------------- Constructor --------------------------*/
    public Alumno(String nombre) {
        setNombre(nombre);                     // valida que el nombre no esté vacío
        this.id    = idCounter++;
        this.notas = new ArrayList<>();
    }

    /*---------------------- Getters & Setters -----------------------*/
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nuevoNombre.trim();
    }

    /** Devuelve una copia para impedir modificaciones externas. */
    public List<Double> getNotas() {
        return new ArrayList<>(notas);
    }

    /*-------------------- Métodos de instancia ----------------------*/
    public void anadirNota(double nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        notas.add(nota);
    }

    public void eliminarNota(int posicion) {
        if (posicion < 0 || posicion >= notas.size()) {
            throw new IndexOutOfBoundsException("Posición de nota inválida.");
        }
        notas.remove(posicion);
    }

    public double calcularMedia() {
        if (notas.isEmpty()) return 0.0;
        double suma = 0;
        for (double n : notas) suma += n;
        return suma / notas.size();
    }

    @Override
    public String toString() {
        String notasStr = notas.isEmpty() ? "Sin notas" : notas.toString();
        return String.format(
            "ID: %d | Nombre: %s | Notas: %s | Media: %.2f",
            id, nombre, notasStr, calcularMedia()
        );
    }

    /*----------------------- Método estático ------------------------*/
    /**
     * Crea un nuevo Alumno, lo añade a la lista recibida y devuelve
     * un mensaje de confirmación.
     */
    public static String anadirAlumno(List<Alumno> listaAlumnas, String nombre) {
        Alumno nueva = new Alumno(nombre);
        listaAlumnas.add(nueva);
        return "Alumna '" + nueva.getNombre() + "' añadida con ID " + nueva.getId() + ".";
    }
}
