package bookflow.service;

import bookflow.dto.BookResponse;
import bookflow.dto.CreateBookRequest;
import bookflow.exception.BookNotFoundException;
import bookflow.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final ConcurrentHashMap<Long, Book> books = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<BookResponse> getAllBooks(){
        return books.values().stream()
                .map(this::toResponse)
                .toList();
    }

    public BookResponse getBook(Long id){
        Book book = books.get(id);
        if (book == null){
            throw new BookNotFoundException(id);
        }
        return toResponse(book);
    }

    public BookResponse createBook(CreateBookRequest request){
        Long newId = idGenerator.incrementAndGet();
        Book book = new Book(newId, request.title(), request.author(), request.available());
        books.put(newId, book);
        return toResponse(book);
    }

    public void deleteBook(Long id){
        // remove() zwraca null gdy klucza nie było wtedy też zgłaszamy 404
        if (books.remove(id) == null){
            throw new BookNotFoundException(id);
        }
    }

    private BookResponse toResponse(Book book){
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.isAvailable());
    }
}
