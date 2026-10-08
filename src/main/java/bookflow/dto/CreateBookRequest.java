package bookflow.dto;

public record CreateBookRequest (String title, String author, boolean available) {
}
