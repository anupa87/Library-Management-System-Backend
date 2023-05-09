package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.category.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book toBook(BookDTO book, Category category){
    return new Book(book.getISBN(), book.getTitle(), book.getImageURL(), book.getDescription(), book.getAuthor(), book.getPublisher(), book.getPublishedYear(), book.getNumberOfCopies(), book.getAvailableCopies(), category);
  }

}
