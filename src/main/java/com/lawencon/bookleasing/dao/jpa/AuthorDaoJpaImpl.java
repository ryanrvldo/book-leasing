package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.AuthorDao;
import com.lawencon.bookleasing.entity.Author;
import com.lawencon.bookleasing.entity.Book;
import com.lawencon.bookleasing.repository.AuthorRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("author-jpa")
public class AuthorDaoJpaImpl implements AuthorDao {

  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorDaoJpaImpl(AuthorRepository authorRepository) {
	this.authorRepository = authorRepository;
  }

  @Override
  public Author findById(Long id) throws Exception {
	return authorRepository.findById(id).get();
  }

  @Override
  public Author getByFirstAndLastName(String firstName, String lastName) throws Exception {
	return authorRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
  }

  @Override
  public void insert(Author data) throws Exception {
	authorRepository.save(data);
  }

  @Override
  public void update(Author data) throws Exception {
	authorRepository.save(data);
  }

  @Override
  public void delete(Author data) throws Exception {
	authorRepository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	authorRepository.deleteById(id);
  }

  @Override
  public List<Author> findAll() throws Exception {
	return authorRepository.findAll();
  }

  @Override
  public List<Author> findAllByBook(Book book) throws Exception {
	return authorRepository.findAllByBook(book.getId());
  }

}
