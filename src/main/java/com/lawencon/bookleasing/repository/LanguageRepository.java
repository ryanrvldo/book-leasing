package com.lawencon.bookleasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.entity.Language;

/**
 * @author Rian Rivaldo
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

  Language findByCode(String code);

}
