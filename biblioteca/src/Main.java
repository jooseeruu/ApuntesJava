public class Main {
    public static void main(String[] args) {
        Library lib = new Library(new EmailNotifier());

        Librarian admin = new Librarian("L1", "Ana");
        Member m1 = new Member("M1", "Juan");
        Member m2 = new Member("M2", "María");

        admin.addBook(lib, new Book("B1", "1984", "George Orwell", Genre.SCIENCE_FICTION));
        admin.addBook(lib, new Book("B2", "El Quijote", "Miguel de Cervantes", Genre.HISTORY));
        admin.addBook(lib, new Book("B3", "La Sombra del Viento", "Carlos Ruiz Zafón", Genre.MYSTERY));

        System.out.println("\nEstado inicial de la biblioteca:");
        lib.listAll();

        m1.borrowBook(lib, "B1");
        m2.borrowBook(lib, "B1");
        m2.borrowBook(lib, "B3");

        System.out.println("\nEstado tras préstamos:");
        lib.listAll();

        m1.returnBook(lib, "B1");
        System.out.println("\nEstado tras devolución:");
        lib.listAll();
    }
}
