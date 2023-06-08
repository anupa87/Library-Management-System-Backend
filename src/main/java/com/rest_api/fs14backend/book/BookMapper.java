package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.category.Category;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {
  public Book newBook(BookDTO bookDTO, Category category, Author author) {
    return new Book(
            category,
            bookDTO.getTitle(),
            bookDTO.getImageURL(),
            bookDTO.getDescription(),
            author,
            bookDTO.getPublisher(),
            bookDTO.getPublishedYear()
            );
  }

}
