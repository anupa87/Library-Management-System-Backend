package com.rest_api.fs14backend.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository < Transaction, UUID > {
}
