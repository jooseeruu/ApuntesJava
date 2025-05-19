public class Principal {

    // Mètode que comprova si un número de targeta és vàlid (Luhn)
    public static boolean validarTargeta(String numero) {
        int suma = 0;
        boolean alterna = false;
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numero.charAt(i));
            if (alterna) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            suma += digit;
            alterna = !alterna;
        }
        return (suma % 10 == 0);
    }

    public static void main(String[] args) {
        String num = "1234567890123452";
        String nom = "Banc 1";

        // 1. Validació
        if (validarTargeta(num)) {
            System.out.println("La targeta 1234 5678 9012 3452 és vàlida.");
        } else {
            System.out.println("La targeta no és vàlida.");
            return;
        }

        // 2. Creació i registre
        Targeta t = new Targeta(num, nom);
        System.out.println("Targeta enregistrada correctament.");

        // 3. Afegir compres
        t.afegirCompra("Farmàcia", 8.60);
        t.afegirCompra("Agència de viatges", 90.88);
        t.afegirCompra("Mercat", 12.55);
        t.afegirCompra("Cafeteria", 4.50);
        t.afegirCompra("Mecànic", 40.00);
        t.afegirCompra("Tenda d’esports", 29.95);

        // 4. Mostrar darreres 4 compres
        System.out.println("Darreres 4 compres realitzades per la targeta " 
            + t.numeroEnmascarat() + ":");
        for (String comp : t.darreresCompres(4)) {
            System.out.println(comp);
        }
    }
}
