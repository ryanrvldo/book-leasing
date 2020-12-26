package com.lawencon.bookleasing.dao.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.User;
import com.lawencon.bookleasing.repository.UserRepository;

/**
 * @author Rian Rivaldo
 */
@Repository("user-jpa")
public class UserDaoJpaImpl implements UserDao {

  private final UserRepository userRepository;

  @Autowired
  public UserDaoJpaImpl(UserRepository userRepository) {
	this.userRepository = userRepository;
  }

  @Override
  public User findById(Long id) throws Exception {
	return this.userRepository.findUserById(id);
  }

  @Override
  public User findByUsername(String username) throws Exception {
	return userRepository.findByUsername(username);
  }

  @Override
  public void insert(User data) throws Exception {
	this.userRepository.save(data);
  }

  @Override
  public void update(User data) throws Exception {
	this.userRepository.save(data);
  }

  @Override
  public void delete(User data) throws Exception {
	this.userRepository.delete(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	this.userRepository.deleteById(id);
  }

  @Override
  public List<User> findAll() throws Exception {
	return this.userRepository.findAll();
  }

}
