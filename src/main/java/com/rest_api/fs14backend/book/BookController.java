package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.category.Category;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.category.CategoryService;
import com.rest_api.fs14backend.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private AuthorService authorService;

  @Autowired
  private BookMapper bookMapper;

  @PostMapping("/books")
  public Book addBook(@RequestBody BookDTO bookDTO) {
    UUID categoryId = bookDTO.getCategoryId();
    Category category = categoryService.getCategoryById(categoryId);

    UUID authorId = bookDTO.getAuthorId();
    Author author = authorService.getAuthorById(authorId);

    Book book = bookMapper.newBook(bookDTO, category, author);
    return bookService.addBook(bookDTO);
  }

  @GetMapping("/books")
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @GetMapping("/books/{bookId}")
  public Book getBookById(@PathVariable UUID bookId) {
    Book book = bookService.getBookById(bookId);
    if (book == null) {
      throw new NotFoundException("Book not found");
    }
    return book;
  }

  @PutMapping("/books/{bookId}")
  public Book updateBook(@PathVariable UUID bookId, @RequestBody BookDTO bookDTO) {
    Book foundBook = bookService.getBookById(bookId);
    if (foundBook == null) {
      throw new NotFoundException("Book not found");
    }

    UUID categoryId = bookDTO.getCategoryId();
    Category category = categoryService.getCategoryById(categoryId);

    UUID authorId = bookDTO.getAuthorId();
    Author author = authorService.getAuthorById(authorId);

    return bookService.updateBook(bookId, bookDTO, category, author);
  }
  @DeleteMapping("/books/{bookId}")
  public void deleteBook(@PathVariable UUID bookId) {
    bookService.deleteBook(bookId);
  }


}
