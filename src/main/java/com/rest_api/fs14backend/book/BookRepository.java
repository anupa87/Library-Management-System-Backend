package com.rest_api.fs14backend.book;
import com.rest_api.fs14backend.category.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
  List<Book> findByCategory(Category category);
}
