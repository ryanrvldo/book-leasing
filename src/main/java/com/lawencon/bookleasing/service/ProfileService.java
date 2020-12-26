package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Profile;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface ProfileService extends BaseService<Profile> {

  Profile getProfileByEmailAndPhone(String email, String phone) throws Exception;

}
