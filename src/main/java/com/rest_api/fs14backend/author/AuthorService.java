package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  public Author getAuthorById(UUID authorId) {
    Optional<Author> authorOptional = authorRepository.findById(authorId);
    if (authorOptional.isPresent()) {
      return authorOptional.get();
    } else {
      throw new NotFoundException("Author not found");
    }
  }

  public Author addAuthor(Author author) {
    return authorRepository.save(author);
  }

  public void deleteAuthor(UUID authorId) {
    Author foundAuthor = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));
    authorRepository.delete(foundAuthor);
  }

  public Author updateAuthor(UUID authorId, Author author) {
    Author foundAuthor = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException("Author not found"));

    foundAuthor.setFullName(author.getFullName());
    foundAuthor.setEmail(author.getEmail());
    return authorRepository.save(foundAuthor);
  }
}
