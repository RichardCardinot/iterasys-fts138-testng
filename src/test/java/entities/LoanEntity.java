package entities;

public class LoanEntity {
    public String userId;
    public ISBN[] collectionOfIsbns; // Lista com os códigos dos livros emprestados
    public String isbn;
    public static class ISBN {
        private final String isbn;

        public ISBN(String isbn) {
            this.isbn = isbn;
        }
    }
}
