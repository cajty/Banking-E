package org.ably.bankinge.repository;

import org.ably.bankinge.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.accountOfSender.id = ?1 OR t.accountOfReceiver.id = ?1")
    List<Transaction> findByUserId(Long userId);

}
