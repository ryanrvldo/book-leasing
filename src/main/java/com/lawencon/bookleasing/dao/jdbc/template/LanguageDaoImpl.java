package com.lawencon.bookleasing.dao.jdbc.template;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageDaoImpl extends BaseDaoImpl implements LanguageDao {

	@Override
	public Language insert(Language request) throws Exception {
		String sql = buildSQLQueryOf(
				"INSERT INTO tb_m_language (code, language_name) ",
				"VALUES (?, ?) ",
				"RETURNING id");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Language language = new Language();
					language.setId(rs.getLong("id"));
					return language;
				},
				request.getCode(),
				request.getName()
		);
	}

	@Override
	public Language get(Language request) throws Exception {
		String sql = buildSQLQueryOf(
				"SELECT id, code ",
				"FROM tb_m_language ",
				"WHERE lower(code) = lower(?) ");
		return queryForObject(sql,
				(rs, rowNum) -> {
					Language language = new Language();
					language.setId(rs.getLong("id"));
					language.setCode(rs.getString("code"));
					return language;
				},
				request.getCode()
		);
	}

	@Override
	public Language update(Language request) throws Exception {
		return null;
	}

	@Override
	public Language delete(Language request) throws Exception {
		return null;
	}

}
