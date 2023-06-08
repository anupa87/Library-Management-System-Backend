package com.rest_api.fs14backend.author;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Author> updateAuthor(@PathVariable UUID authorId, @RequestBody Author author) {
    Author foundAuthor = authorService.getAuthorById(authorId);
    if (foundAuthor != null) {
      Author updatedAuthor = authorService.updateAuthor(authorId, author);
      return ResponseEntity.ok(updatedAuthor);
    } else {
      throw new NotFoundException("Author not found");
    }
  }

  @DeleteMapping("/authors/{authorId}")
  public void deleteAuthor(@PathVariable UUID authorId) {
    authorService.deleteAuthor(authorId);
  }
}
