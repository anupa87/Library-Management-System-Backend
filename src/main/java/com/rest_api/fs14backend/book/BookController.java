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
  public List<Book> findAll() {
    return bookService.findAll();
  }

  @GetMapping("/{id}")
  public Book findById(@PathVariable UUID id){
    Book book = bookService.findById(id);

    if(book == null){
      throw new NotFoundException("Book not found");
    }
    return book;
  }

  @DeleteMapping("/{id}")
  public void deleteBook(@PathVariable UUID id) {
    bookService.deleteBook(id);
  }

  @PutMapping ("/{id}")
  public Book updateBook(@PathVariable UUID id, @RequestBody Book book){
    Book foundBook = bookService.findById(id);
    if(foundBook != null){
      Book updatedBook = bookService.updateBook(id, book);
      return updatedBook;
    } else {
      throw new NotFoundException("Book not found");
    }
  }

}
