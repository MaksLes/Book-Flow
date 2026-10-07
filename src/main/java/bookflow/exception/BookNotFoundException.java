package bookflow.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("Nie znaleziono książki o ID: " + id);
    }
}
