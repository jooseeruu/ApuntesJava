public class Book {
    private final String id;
    private String title;
    private String author;
    private Genre genre;
    private boolean available;

    public Book(String id, String title, String author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    public String getId() { return id; }
    public boolean isAvailable() { return available; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public void borrow() throws BookNotAvailableException {
        if (!available) {
            throw new BookNotAvailableException("El libro '" + title + "' no está disponible.");
        }
        available = false;
    }

    public void returned() {
        available = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s (%s) %s",
            id, title, author, genre,
            available ? "(disponible)" : "(prestado)");
    }
}
