public class VaixellRecreatiu extends Vaixell {

    public VaixellRecreatiu(String matricula, double longitud, double calada) {
        super(matricula, longitud, calada);
    }

    @Override
    public String getTipus() {
        return "Recreatiu";
    }
}
