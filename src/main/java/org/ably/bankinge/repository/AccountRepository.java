package org.ably.bankinge.repository;

import org.ably.bankinge.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
     List<Account> findByUserId(Long userId);
}
