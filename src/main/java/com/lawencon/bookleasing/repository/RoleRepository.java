package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Role;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findByCode(String code) throws Exception;

}
