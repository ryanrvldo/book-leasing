package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Role;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface RoleService extends BaseService<Role> {

  Role getRoleByCode(String code) throws Exception;
}
