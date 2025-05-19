public class VaixellComercial extends Vaixell {
    private final boolean perillosos;

    public VaixellComercial(String matricula, double longitud, double calada, boolean perillosos) {
        super(matricula, longitud, calada);
        this.perillosos = perillosos;
    }

    public boolean isPerillosos() {
        return perillosos;
    }
    @Override
    public String getTipus() {
        return "Comercial";
    }
}
