package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ProfileDao extends BaseDao<Profile> {

  Profile findByEmailAndPhone(String email, String phone) throws Exception;

}
