package bookflow.dto;

//DTO wyjściowe - to wysyłamy do klienta
public record BookResponse(Long id, String title, String author, boolean available) {
}
