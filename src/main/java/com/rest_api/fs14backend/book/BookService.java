package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
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

  public List<Book> findAll() {
    return bookRepository.findAll();
  }

  public Book findById(UUID id) {
    return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not found"));
  }

  public void deleteBook(UUID id) {
    Book foundBook = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not found"));
    bookRepository.delete(foundBook);
  }

  public Book updateBook(UUID id, Book book){
    Book foundBook = bookRepository.findById(id).orElseThrow(()-> new NotFoundException("book not found"));

    foundBook.setISBN(book.getISBN());
    foundBook.setTitle(book.getTitle());
    foundBook.setImageURL(book.getImageURL());
    foundBook.setDescription(book.getDescription());
    foundBook.setAuthor(book.getAuthor());
    foundBook.setPublisher(book.getPublisher());
    foundBook.setPublishedYear(book.getPublishedYear());
    foundBook.setNumberOfCopies(book.getNumberOfCopies());
    foundBook.setAvailableCopies(book.getAvailableCopies());
    return bookRepository.save(foundBook);

  }

}
