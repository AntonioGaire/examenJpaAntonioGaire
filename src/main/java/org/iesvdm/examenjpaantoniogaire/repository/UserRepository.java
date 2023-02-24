package org.iesvdm.examenjpaantoniogaire.repository;

import org.iesvdm.examenjpaantoniogaire.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
