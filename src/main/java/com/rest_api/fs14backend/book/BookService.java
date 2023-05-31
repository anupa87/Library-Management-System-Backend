package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.author.AuthorRepository;
import com.rest_api.fs14backend.category.Category;
import com.rest_api.fs14backend.category.CategoryRepository;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRepository authorRepository;

  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private BookMapper bookMapper;

  public Book addBook(BookDTO bookDTO) {
    Category category = categoryRepository.findById(bookDTO.getCategoryId())
            .orElseThrow(() -> new NotFoundException("Category not found"));

    Author author = authorRepository.findById(bookDTO.getAuthorId())
            .orElseThrow(() -> new NotFoundException("Author not found"));

    Book book = new Book(
            category,
            bookDTO.getTitle(),
            bookDTO.getImageURL(),
            bookDTO.getDescription(),
            author,
            bookDTO.getPublisher(),
            bookDTO.getPublishedYear(),
            bookDTO.getStatus()
    );

    return bookRepository.save(book);
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book getBookById(UUID bookId) {
    return bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("book not found"));
  }

  public void deleteBook(UUID bookId) {
    Book foundBook = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("book not found"));
    bookRepository.delete(foundBook);
  }

  @Transactional
  public Book updateBook(UUID bookId, BookDTO bookDTO, Category category, Author author) {
    Book foundBook = bookRepository.findById(bookId)
            .orElseThrow(() -> new NotFoundException("book not found"));
    foundBook.setCategory(category);
    foundBook.setTitle(bookDTO.getTitle());
    foundBook.setImageURL(bookDTO.getImageURL());
    foundBook.setDescription(bookDTO.getDescription());
    foundBook.setAuthor(author);
    foundBook.setPublisher(bookDTO.getPublisher());
    foundBook.setPublishedYear(bookDTO.getPublishedYear());
    foundBook.setStatus(bookDTO.getStatus());



    return bookRepository.save(foundBook);
  }
}
