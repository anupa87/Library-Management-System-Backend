package com.rest_api.fs14backend.book;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book toBook(BookDTO book, Category category) {
    return new Book( book.getTitle(), book.getImageURL(), book.getDescription(), book.getAuthorList(), book.getPublisher(), book.getPublishedYear(), book.getNumberOfCopies(), book.getAvailableCopies(), category);
  }
}
