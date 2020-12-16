package com.lawencon.bookleasing.dao.impl;

import com.lawencon.bookleasing.dao.LanguageDao;
import com.lawencon.bookleasing.entity.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rian Rivaldo Rumapea
 */
public class LanguageDaoImpl extends BaseDaoImpl implements LanguageDao {

	@Override
	public Language insert(Language request) throws Exception {
		String query = buildSQLQueryOf(
				"INSERT INTO tb_m_language (code, language_name) ",
				"VALUES (?, ?) ",
				"RETURNING id");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getCode());
		statement.setString(2, request.getName());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Language(resultSet.getLong("id"));
		}
		return null;
	}

	@Override
	public Language get(Language request) throws Exception {
		String query = buildSQLQueryOf(
				"SELECT id, code ",
				"FROM tb_m_language ",
				"WHERE lower(code) = lower(?) ");
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, request.getCode());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new Language(resultSet.getLong("id"), resultSet.getString("code"));
		}
		return null;
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
