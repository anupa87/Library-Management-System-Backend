package com.rest_api.fs14backend.borrow;

import com.rest_api.fs14backend.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository <Borrow, UUID> {
  List<Borrow> findByBook(Book book);
}
