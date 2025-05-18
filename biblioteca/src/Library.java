import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private Notifier notifier;

    public Library(Notifier notifier) {
        this.notifier = notifier;
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public Book lendBook(Person p, String bookId) throws BookNotAvailableException {
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                b.borrow();
                LocalDate due = LocalDate.now().plusWeeks(2);
                notifier.notify(p, "Libro prestado. Devuélvelo antes de " + due);
                return b;
            }
        }
        throw new BookNotAvailableException("No existe un libro con ID " + bookId);
    }

    public void receiveBook(Person p, String bookId) {
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                b.returned();
                notifier.notify(p, "Gracias por devolver: " + b.getTitle());
                return;
            }
        }
        System.err.println("ID de libro desconocido: " + bookId);
    }

    public void listAll() {
        books.forEach(System.out::println);
    }
}
