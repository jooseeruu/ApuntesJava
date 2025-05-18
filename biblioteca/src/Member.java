import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private List<Book> borrowedBooks = new ArrayList<>();

    public Member(String id, String name) {
        super(id, name);
    }

    @Override
    public String getRole() {
        return "Member";
    }

    public void borrowBook(Library library, String bookId) {
        try {
            Book b = library.lendBook(this, bookId);
            borrowedBooks.add(b);
            System.out.println(name + " ha prestado: " + b.getTitle());
        } catch (BookNotAvailableException e) {
            System.err.println("No se pudo prestar: " + e.getMessage());
        }
    }

    public void returnBook(Library library, String bookId) {
        library.receiveBook(this, bookId);
        borrowedBooks.removeIf(b -> b.getId().equals(bookId));
        System.out.println(name + " ha devuelto el libro con ID " + bookId);
    }
}
