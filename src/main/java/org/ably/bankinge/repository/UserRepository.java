package org.ably.bankinge.repository;

import org.ably.bankinge.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

}
