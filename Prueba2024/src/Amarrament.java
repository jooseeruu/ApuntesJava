public class Amarrament {
    private static int idCounter = 1;
    private final int id;
    private final double longitud;
    private final double calada;
    private final String tipus;      // "Comercial" o "Recreatiu"
    private Vaixell ocupatPer;       // null si està lliure

    public Amarrament(double longitud, double calada, String tipus) {
        this.id = idCounter++;
        this.longitud = longitud;
        this.calada = calada;
        this.tipus = tipus;
        this.ocupatPer = null;
    }

    public int getId() {
        return id;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getCalada() {
        return calada;
    }

    public String getTipus() {
        return tipus;
    }

    public Vaixell getOcupatPer() {
        return ocupatPer;
    }

    public boolean estaLliure() {
        return ocupatPer == null;
    }

    public void ocupar(Vaixell v) {
        this.ocupatPer = v;
    }

    public void alliberar() {
        this.ocupatPer = null;
    }
}
