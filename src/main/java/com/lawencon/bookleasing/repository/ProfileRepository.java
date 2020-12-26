package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Profile findByEmailAndPhone(String email, String phone) throws Exception;

}
