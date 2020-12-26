package com.lawencon.bookleasing.dao;

import com.lawencon.bookleasing.entity.Language;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface LanguageDao extends BaseDao<Language> {

  Language findByCode(String code) throws Exception;

}
