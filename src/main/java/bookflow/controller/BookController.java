package bookflow.controller;

import bookflow.dto.BookResponse;
import bookflow.dto.CreateBookRequest;
import bookflow.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    //Wstrzykiwanie zależności przez konstruktor
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // GET /api/books -> 200 OK
    @GetMapping
    public List<BookResponse> getAll(){
        return bookService.getAllBooks();
    }

    // GET /api/books/{id} -> 200 OK lub 404
    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id){
        return bookService.getBook(id);
    }

    //POST /api/books -> 201 Created
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody CreateBookRequest request){
        return bookService.createBook(request);
    }

    //DELETE /api/books/{id} -> 204 No content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
