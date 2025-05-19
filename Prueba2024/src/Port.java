import java.util.ArrayList;
import java.util.List;

public class Port {
    private final List<Amarrament> amarraments = new ArrayList<>();
    private final List<Vaixell> pendents = new ArrayList<>();

    /** Afegir un nou amarrament al port */
    public void afegirAmarrament(double longitud, double calada, String tipus) {
        amarraments.add(new Amarrament(longitud, calada, tipus));
        System.out.println("Amarrament afegit: tipus=" + tipus +
                           ", longitud=" + longitud + ", calada=" + calada);
    }

    /** Afegeix un vaixell a la llista de pendents, sense matrícules duplicades */
    public void afegirVaixell(Vaixell v) {
        // comprovar matrícula duplicada en pendents
        for (Vaixell x : pendents) {
            if (x.getMatricula().equalsIgnoreCase(v.getMatricula())) {
                System.out.println("Error: ja existeix un vaixell pendent amb aquesta matrícula.");
                return;
            }
        }
        // comprovar en amarraments ocupats
        for (Amarrament a : amarraments) {
            if (!a.estaLliure() && a.getOcupatPer().getMatricula().equalsIgnoreCase(v.getMatricula())) {
                System.out.println("Error: aquesta matrícula ja està amarrada.");
                return;
            }
        }
        pendents.add(v);
        System.out.println("Vaixell afegit a pendents: " + v.getMatricula());
    }

    /** Assigna automàticament amarraments a tots els pendents */
    public void assignarAmarrament() {
        List<Vaixell> assignats = new ArrayList<>();

        for (Vaixell v : pendents) {
            for (int i = 0; i < amarraments.size(); i++) {
                Amarrament a = amarraments.get(i);
                if (a.estaLliure()
                    && a.getTipus().equals(v.getTipus())
                    && a.getLongitud() >= v.getLongitud()
                    && a.getCalada() >= v.getCalada()
                    && (!esConsecutivePerilloses(a, v))) {
                    a.ocupar(v);
                    assignats.add(v);
                    System.out.println("Assignat " + v.getMatricula() +
                                       " a amarrament ID " + a.getId());
                    break;
                }
            }
        }
        // esborrem assignats de la llista de pendents
        pendents.removeAll(assignats);
        if (assignats.isEmpty()) {
            System.out.println("No s'ha pogut assignar cap vaixell pendent.");
        }
    }

    /** Comprova si assignar v a l'amarrament a crearia dues comercials perilloses seguides */
    private boolean esConsecutivePerilloses(Amarrament a, Vaixell v) {
        if (!(v instanceof VaixellComercial) || !((VaixellComercial) v).isPerillosos()) {
            return false;
        }
        // indices veïns
        int idx = amarraments.indexOf(a);
        // veí anterior
        if (idx > 0) {
            Amarrament prev = amarraments.get(idx - 1);
            if (!prev.estaLliure()
                && prev.getOcupatPer() instanceof VaixellComercial
                && ((VaixellComercial) prev.getOcupatPer()).isPerillosos()) {
                return true;
            }
        }
        // veí següent
        if (idx < amarraments.size() - 1) {
            Amarrament next = amarraments.get(idx + 1);
            if (!next.estaLliure()
                && next.getOcupatPer() instanceof VaixellComercial
                && ((VaixellComercial) next.getOcupatPer()).isPerillosos()) {
                return true;
            }
        }
        return false;
    }

    /** Allibera l'amarrament ocupat pel vaixell amb aquesta matrícula */
    public void alliberarAmarrament(String matricula) {
        for (Amarrament a : amarraments) {
            if (!a.estaLliure() && a.getOcupatPer().getMatricula().equalsIgnoreCase(matricula)) {
                a.alliberar();
                System.out.println("Amarrament ID " + a.getId() +
                                   " alliberat del vaixell " + matricula);
                return;
            }
        }
        System.out.println("Cap vaixell amb matrícula " + matricula + " estava amarrat.");
    }

    /** Mostra estat dels amarraments i la cua de pendents */
    public void mostrarEstatAmarraments() {
        System.out.println("=== Estat amarraments ===");
        for (Amarrament a : amarraments) {
            if (a.estaLliure()) {
                System.out.println("ID " + a.getId() + ": lliure (" +
                                   a.getTipus() + ", " + a.getLongitud() +
                                   "x" + a.getCalada() + ")");
            } else {
                Vaixell v = a.getOcupatPer();
                System.out.println("ID " + a.getId() + ": ocupat per " +
                                   v.getTipus() + " " + v.getMatricula());
            }
        }
        System.out.println("=== Vaixells en espera ===");
        if (pendents.isEmpty()) {
            System.out.println("Cap vaixell pendent.");
        } else {
            for (Vaixell v : pendents) {
                System.out.print(v.getTipus() + " " + v.getMatricula());
                if (v instanceof VaixellComercial) {
                    System.out.print(" (perillós=" + ((VaixellComercial) v).isPerillosos() + ")");
                }
                System.out.println();
            }
        }
    }
}
