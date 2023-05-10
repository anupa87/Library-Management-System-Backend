package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping
  public Book addBook(@RequestBody Book book){
    return bookService.addBook(book);
  }
  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/{bookId}")
  public Book getBookById(@PathVariable UUID bookId){
    Book book = bookService.getBookById(bookId);

    if(book == null){
      throw new NotFoundException("Book not found");
    }
    return book;
  }

  @DeleteMapping("/{bookId}")
  public void deleteBook(@PathVariable UUID bookId) {
    bookService.deleteBook(bookId);
  }

  @PutMapping ("/{bookId}")
  public Book updateBook(@PathVariable UUID bookId, @RequestBody Book book){
    Book foundBook = bookService.getBookById(bookId);
    if(foundBook != null){
      Book updatedBook = bookService.updateBook(bookId, book);
      return updatedBook;
    } else {
      throw new NotFoundException("Book not found");
    }
  }

}
