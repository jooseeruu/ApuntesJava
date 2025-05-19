public abstract class Vaixell {
    protected final String matricula;
    protected final double longitud;
    protected final double calada;

    public Vaixell(String matricula, double longitud, double calada) {
        this.matricula = matricula;
        this.longitud = longitud;
        this.calada = calada;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getLongitud() {
        return longitud;
    }

    public double getCalada() {
        return calada;
    }

    /** Retorna "Comercial" o "Recreatiu" */
    public abstract String getTipus();
}
