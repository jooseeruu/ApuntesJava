import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Targeta {
    private String numero;         // 16 dígits
    private boolean activa;        // true = activa, false = bloquejada
    private String nom;            // nom identificatiu
    private List<Compra> compres;  // llista de compres

    // Classe interna per representar una compra
    private static class Compra {
        private String establiment;
        private double importEuros;

        public Compra(String establiment, double importEuros) {
            this.establiment = establiment;
            this.importEuros = importEuros;
        }

        @Override
        public String toString() {
            return establiment + " " + String.format("%.2f€", importEuros);
        }
    }

    // Constructor: estado sempre actiu
    public Targeta(String numero, String nom) {
        if (numero == null || numero.length() != 16) {
            throw new IllegalArgumentException("El número de targeta ha de tenir 16 dígits.");
        }
        this.numero = numero;
        this.nom = nom;
        this.activa = true;
        this.compres = new ArrayList<>();
    }

    // Getters i Setters
    public String getNumero() {
        return numero;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Afegeix una compra
    public void afegirCompra(String establiment, double importEuros) {
        if (!activa) {
            throw new IllegalStateException("No es poden afegir compres a una targeta bloquejada.");
        }
        compres.add(new Compra(establiment, importEuros));
    }

    // Retorna les n darreres compres (ordre de més recent a més antic)
    public List<String> darreresCompres(int n) {
        int size = compres.size();
        int from = Math.max(0, size - n);
        List<Compra> sub = compres.subList(from, size);
        List<String> resultat = new ArrayList<>();
        // invertim per mostrar de més recent a més antic
        List<Compra> invers = new ArrayList<>(sub);
        Collections.reverse(invers);
        for (Compra c : invers) {
            resultat.add(c.toString());
        }
        return resultat;
    }

    // Número enmascarat: **** **** **** 3452
    public String numeroEnmascarat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            if (i % 4 == 0 && i > 0) sb.append(' ');
            sb.append('*');
        }
        sb.append(' ');
        sb.append(numero.substring(12, 16));
        return sb.toString();
    }
}
// Exemple d'ús
/*
public class Main {
    public static void main(String[] args) {
        Targeta targeta = new Targeta("123
4567890123456", "Targeta de Prova");
        targeta.afegirCompra("Botiga A", 50.0);
        targeta.afegirCompra("Botiga B", 20.0);
        targeta.afegirCompra("Botiga C", 30.0);

        System.out.println("Número enmascarat: " + targeta.numeroEnmascarat());
        System.out.println("Darreres compres: " + targeta.darreresCompres(2));
    }
}
*/
