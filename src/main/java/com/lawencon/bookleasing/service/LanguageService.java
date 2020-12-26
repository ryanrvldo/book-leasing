package com.lawencon.bookleasing.service;

import com.lawencon.bookleasing.entity.Language;

/**
 * @author Rian Rivaldo Rumapea
 */
public interface LanguageService extends BaseService<Language> {

  Language getLanguageByCode(String code) throws Exception;
}
