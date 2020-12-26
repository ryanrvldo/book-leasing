package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "FROM User u "
      + "INNER JOIN u.profile "
      + "INNER JOIN u.role "
      + "WHERE lower(u.username) = lower(?1) ")
  User findByUsername(String username) throws Exception;

  @Query(value = "FROM User u "
      + "INNER JOIN u.profile "
      + "INNER JOIN u.role "
      + "WHERE u.id = ?1 ")
  User findUserById(Long id) throws Exception;

}
