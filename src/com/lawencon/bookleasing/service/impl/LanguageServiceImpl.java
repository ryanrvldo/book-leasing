package com.lawencon.bookleasing.service.impl;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.dao.impl.LanguageDaoImpl;
import com.lawencon.bookleasing.model.Language;
import com.lawencon.bookleasing.service.LanguageService;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageServiceImpl implements LanguageService {

  private LanguageDao dao = new LanguageDaoImpl();

  @Override
  public Language add(Language newLanguage) throws Exception {
    return dao.insert(newLanguage);
  }

  @Override
  public Language get(Language language) throws Exception {
    return dao.get(language);
  }

}
