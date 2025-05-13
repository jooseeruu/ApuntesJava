import java.util.*;

/**
 * Sistema de adjudicación de plazas para interinos del Govern de les Illes Balears.
 * <p>
 * Incluye las clases:
 * <ul>
 *   <li>Persona (base)</li>
 *   <li>Docente</li>
 *   <li>Sanitario</li>
 *   <li>Plaza</li>
 * </ul>
 * Y los métodos estáticos solicitados:
 * <ul>
 *   <li>adjudicarPlazas</li>
 *   <li>mostrarAdjudicaciones</li>
 *   <li>simulateAdjudicacion (crea datos de prueba)</li>
 * </ul>
 */
public class AdjudicacionInterinos {

    public static void main(String[] args) {
        simulateAdjudicacion();
    }

    /**
     * Genera datos de ejemplo, adjudica las plazas y muestra el resultado.
     */
    public static void simulateAdjudicacion() {
        List<Plaza> plazas = Arrays.asList(
                new Plaza(1, 'D'),
                new Plaza(2, 'D'),
                new Plaza(3, 'S'),
                new Plaza(4, 'S')
        );

        List<Persona> personas = Arrays.asList(
                new Docente("María", "García", "López", "Matemáticas", 65.5),
                new Docente("Juan", "Martínez", "Pérez", "Historia", 70.0),
                new Sanitario("Ana", "Sánchez", "Ruiz", "Enfermería", 120),
                new Sanitario("Carlos", "Gómez", "Díaz", "Pediatría", 95)
        );

        adjudicarPlazas(plazas, new ArrayList<>(personas));
        mostrarAdjudicaciones(plazas);
    }

    /**
     * Asigna las plazas recibidas a las personas disponibles siguiendo el criterio de mayor puntuación o
     * mayor número de días trabajados, según corresponda al tipo de plaza.
     *
     * @param plazas     Lista de plazas (orden de adjudicación)
     * @param disponibles Lista de interinos disponibles (mezcla de Docente y Sanitario)
     */
    public static void adjudicarPlazas(List<Plaza> plazas, List<Persona> disponibles) {
        for (Plaza plaza : plazas) {
            Persona mejor = null;
            if (plaza.getTipo() == 'D') {
                mejor = disponibles.stream()
                        .filter(p -> p instanceof Docente)
                        .map(p -> (Docente) p)
                        .max(Comparator.comparingDouble(Docente::getPuntos))
                        .orElse(null);
            } else if (plaza.getTipo() == 'S') {
                mejor = disponibles.stream()
                        .filter(p -> p instanceof Sanitario)
                        .map(p -> (Sanitario) p)
                        .max(Comparator.comparingInt(Sanitario::getDiasTrabajados))
                        .orElse(null);
            }

            if (mejor != null) {
                plaza.setPersona(mejor);
                plaza.setAdjudicada(true);
                disponibles.remove(mejor);
            }
        }
    }

    /**
     * Muestra por pantalla las plazas adjudicadas, primero las sanitarias y luego las docentes.
     *
     * @param plazas Lista de plazas ya adjudicadas (o no)
     */
    public static void mostrarAdjudicaciones(List<Plaza> plazas) {
        System.out.println("=== Adjudicaciones Sanitarios ===");
        plazas.stream()
                .filter(p -> p.getTipo() == 'S')
                .forEach(System.out::println);

        System.out.println("\n=== Adjudicaciones Docentes ===");
        plazas.stream()
                .filter(p -> p.getTipo() == 'D')
                .forEach(System.out::println);
    }

    // --------------------------------------------
    // Clases de dominio
    // --------------------------------------------

    /**
     * Clase base Persona con atributos comunes.
     */
    static class Persona {
        private static int contadorPersonas = 0;
        private final int id;
        private String nombre;
        private String apellido1;
        private String apellido2;

        public Persona(String nombre, String apellido1, String apellido2) {
            this.id = ++contadorPersonas;
            this.nombre = nombre;
            this.apellido1 = apellido1;
            this.apellido2 = apellido2;
        }

        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getApellido1() { return apellido1; }
        public void setApellido1(String apellido1) { this.apellido1 = apellido1; }
        public String getApellido2() { return apellido2; }
        public void setApellido2(String apellido2) { this.apellido2 = apellido2; }
        public static int getContadorPersonas() { return contadorPersonas; }

        public String getNombreCompleto() {
            return nombre + " " + apellido1 + " " + apellido2;
        }

        @Override
        public String toString() {
            return String.format("%s (ID %d)", getNombreCompleto(), id);
        }
    }

    /**
     * Especialización de Persona para Docentes.
     */
    static class Docente extends Persona {
        private String titulacion;
        private double puntos;

        public Docente(String nombre, String apellido1, String apellido2,
                       String titulacion, double puntos) {
            super(nombre, apellido1, apellido2);
            this.titulacion = titulacion;
            this.puntos = puntos;
        }

        public String getTitulacion() { return titulacion; }
        public void setTitulacion(String titulacion) { this.titulacion = titulacion; }
        public double getPuntos() { return puntos; }
        public void setPuntos(double puntos) { this.puntos = puntos; }

        @Override
        public String toString() {
            return super.toString() + String.format(" | Docente, Titulación: %s, Puntos: %.2f",
                    titulacion, puntos);
        }
    }

    /**
     * Especialización de Persona para Sanitarios.
     */
    static class Sanitario extends Persona {
        private String especialidad;
        private int diasTrabajados;

        public Sanitario(String nombre, String apellido1, String apellido2,
                         String especialidad, int diasTrabajados) {
            super(nombre, apellido1, apellido2);
            this.especialidad = especialidad;
            this.diasTrabajados = diasTrabajados;
        }

        public String getEspecialidad() { return especialidad; }
        public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
        public int getDiasTrabajados() { return diasTrabajados; }
        public void setDiasTrabajados(int diasTrabajados) { this.diasTrabajados = diasTrabajados; }

        @Override
        public String toString() {
            return super.toString() + String.format(" | Sanitario, Especialidad: %s, Días trabajados: %d",
                    especialidad, diasTrabajados);
        }
    }

    /**
     * Representa una plaza vacante o adjudicada.
     */
    static class Plaza {
        private final int idPlaza;
        private final char tipo; // 'D' para docentes, 'S' para sanitarios
        private boolean adjudicada;
        private Persona persona;

        public Plaza(int idPlaza, char tipo) {
            this.idPlaza = idPlaza;
            this.tipo = tipo;
            this.adjudicada = false;
        }

        public int getIdPlaza() { return idPlaza; }
        public char getTipo() { return tipo; }
        public boolean isAdjudicada() { return adjudicada; }
        public void setAdjudicada(boolean adjudicada) { this.adjudicada = adjudicada; }
        public Persona getPersona() { return persona; }
        public void setPersona(Persona persona) { this.persona = persona; }

        @Override
        public String toString() {
            return String.format("Plaza %d (%c) - %s",
                    idPlaza,
                    tipo,
                    adjudicada ? persona.getNombreCompleto() : "Vacante");
        }
    }
}
