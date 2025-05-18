public class Librarian extends Person {
    public Librarian(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Librarian";
    }

    public void addBook(Library library, Book book) {
        library.addBook(book);
        System.out.println("Librarian ha añadido: " + book.getTitle());
    }
}