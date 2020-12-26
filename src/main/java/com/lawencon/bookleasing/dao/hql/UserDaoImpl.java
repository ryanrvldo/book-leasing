package com.lawencon.bookleasing.dao.hql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.bookleasing.dao.UserDao;
import com.lawencon.bookleasing.entity.Profile;
import com.lawencon.bookleasing.entity.Role;
import com.lawencon.bookleasing.entity.User;

/**
 * @author Rian Rivaldo
 */
@Repository("user-hql")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

  public UserDaoImpl() {
	super(User.class);
  }

  @Override
  public User findById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "SELECT u.id, u.username, u.password, p.id AS profileId, p.fullName, p.email, p.phone,  r.id AS roleId, r.code, r.name ",
	    "FROM User AS u ",
	    "INNER JOIN Profile AS p ON p.id = u.profile.id ",
	    "INNER JOIN Role AS r ON r.id = u.role.id ",
	    "WHERE u.id = ?1 ");

	Object[] objArr = getCustomSingleResult(hql, Object[].class,
	    id);
	User user = new User();
	user.setId((Long) objArr[0]);
	user.setUsername((String) objArr[1]);
	user.setPassword((String) objArr[2]);

	Profile profile = new Profile();
	profile.setId((Long) objArr[3]);
	profile.setFullName((String) objArr[4]);
	profile.setEmail((String) objArr[5]);
	profile.setPhone((String) objArr[6]);
	user.setProfile(profile);

	Role role = new Role();
	role.setId((Long) objArr[7]);
	role.setCode((String) objArr[8]);
	role.setName((String) objArr[9]);
	user.setRole(role);
	return user;
  }

  @Override
  public User findByUsername(String username) throws Exception {
	String hql = buildQueryOf(
	    "SELECT u.id, u.username, u.password, p.id AS profileId, p.fullName, p.email, p.phone,  r.id AS roleId, r.code, r.name ",
	    "FROM User AS u ",
	    "INNER JOIN Profile AS p ON p.id = u.profile.id ",
	    "INNER JOIN Role AS r ON r.id = u.role.id ",
	    "WHERE lower(u.username) = lower(?1) ");

	Object[] objArr = getCustomSingleResult(hql, Object[].class,
	    username);
	User user = new User();
	user.setId((Long) objArr[0]);
	user.setUsername((String) objArr[1]);
	user.setPassword((String) objArr[2]);

	Profile profile = new Profile();
	profile.setId((Long) objArr[3]);
	profile.setFullName((String) objArr[4]);
	profile.setEmail((String) objArr[5]);
	profile.setPhone((String) objArr[6]);
	user.setProfile(profile);

	Role role = new Role();
	role.setId((Long) objArr[7]);
	role.setCode((String) objArr[8]);
	role.setName((String) objArr[9]);
	user.setRole(role);
	return user;
  }

  @Override
  public void insert(User data) throws Exception {
	insertData(data);
  }

  @Override
  public void update(User data) throws Exception {
	mergeUpdateData(data);
  }

  @Override
  public void delete(User data) throws Exception {
	deleteData(data);
  }

  @Override
  public void deleteById(Long id) throws Exception {
	String hql = buildQueryOf(
	    "DELETE FROM User ",
	    "WHERE id = ?1 ");
	deleteEntityById(hql, id);
  }

  @Override
  public List<User> findAll() throws Exception {
	return getAllData();
  }

}
