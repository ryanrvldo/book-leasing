package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Role;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RoleDao extends BaseDao<Role> {

  Role findByCode(String code) throws Exception;

}
