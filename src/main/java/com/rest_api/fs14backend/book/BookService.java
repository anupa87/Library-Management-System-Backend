package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public Book addBook(Book book) {
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

  public Book updateBook(UUID bookId, Book book) {
    Book foundBook = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("book not found"));

    foundBook.setISBN(book.getISBN());
    foundBook.setTitle(book.getTitle());
    foundBook.setImageURL(book.getImageURL());
    foundBook.setDescription(book.getDescription());
    foundBook.setAuthors(book.getAuthors());
    foundBook.setPublisher(book.getPublisher());
    foundBook.setPublishedYear(book.getPublishedYear());
    foundBook.setNumberOfCopies(book.getNumberOfCopies());
    foundBook.setAvailableCopies(book.getAvailableCopies());
    return bookRepository.save(foundBook);

  }

}
