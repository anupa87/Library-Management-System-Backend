package com.rest_api.fs14backend.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @PostMapping ("/authors")
  public Author addAuthor(@RequestBody Author author) {
    return authorService.addAuthor(author);
  }

  @GetMapping ("/authors")
  public List<Author> getAllAuthors() {
    return authorService.getAllAuthors();
  }

  @GetMapping("/authors/{authorId}")
  public Author getAuthorById(@PathVariable UUID authorId) {
    return authorService.getAuthorById(authorId);
  }

  @PutMapping("/authors/{authorId}")
  public Author updateAuthor(@PathVariable UUID authorId, @RequestBody Author author) {
    return authorService.updateAuthor(authorId, author);
  }

  @DeleteMapping("/authors/{authorId}")
  public void deleteAuthor(@PathVariable UUID authorId) {
    authorService.deleteAuthor(authorId);
  }
}
