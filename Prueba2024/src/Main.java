import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Port port = new Port();
        Scanner in = new Scanner(System.in);
        int opc;

        do {
            System.out.println("\n--- Menú Port ---");
            System.out.println("1) Afegir amarrament");
            System.out.println("2) Afegir vaixell pendent");
            System.out.println("3) Assignar amarraments");
            System.out.println("4) Alliberar amarrament");
            System.out.println("5) Mostrar estat");
            System.out.println("0) Sortir");
            System.out.print("Opció: ");
            opc = in.nextInt();
            in.nextLine(); // neteja buffer

            switch (opc) {
                case 1:
                    System.out.print("Longitud amarrament: ");
                    double L = in.nextDouble();
                    System.out.print("Calada amarrament: ");
                    double C = in.nextDouble();
                    in.nextLine();
                    System.out.print("Tipus (Comercial/Recreatiu): ");
                    String t = in.nextLine();
                    port.afegirAmarrament(L, C, t);
                    break;
                case 2:
                    System.out.print("Matricula vaixell: ");
                    String m = in.nextLine();
                    System.out.print("Longitud vaixell: ");
                    double vl = in.nextDouble();
                    System.out.print("Calada vaixell: ");
                    double vc = in.nextDouble();
                    in.nextLine();
                    System.out.print("Tipus (C=Comercial, R=Recreatiu): ");
                    String tip = in.nextLine();
                    if (tip.equalsIgnoreCase("C")) {
                        System.out.print("És perillós? (true/false): ");
                        boolean p = in.nextBoolean();
                        in.nextLine();
                        port.afegirVaixell(new VaixellComercial(m, vl, vc, p));
                    } else {
                        port.afegirVaixell(new VaixellRecreatiu(m, vl, vc));
                    }
                    break;
                case 3:
                    port.assignarAmarrament();
                    break;
                case 4:
                    System.out.print("Matricula a alliberar: ");
                    String ma = in.nextLine();
                    port.alliberarAmarrament(ma);
                    break;
                case 5:
                    port.mostrarEstatAmarraments();
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        } while (opc != 0);

        in.close();
    }
}

