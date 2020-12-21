package com.lawencon.bookleasing.dao.jdbc.template;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Rian Rivaldo Rumapea
 */
class BaseDaoImpl<T> {

	private JdbcTemplate jdbcTemplate;

	protected void queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
			throws Exception {
		Entry<Object[], int[]> argsEntry = buildArgsOf(args);
		jdbcTemplate.query(sql, argsEntry.getKey(), argsEntry.getValue(), rowMapper);
	}

	protected List<T> query(String sql, RowMapper<T> rowMapper) {
		return jdbcTemplate.query(sql, rowMapper);
	}

	private Entry<Object[], int[]> buildArgsOf(Object... args) throws Exception {
		int argSize = args.length;
		int[] argTypes = new int[argSize];
		for (int i = 0; i < argSize; i++) {
			Object arg = args[i];
			if (arg instanceof String) {
				argTypes[i] = Types.VARCHAR;
			} else if (arg instanceof Short) {
				argTypes[i] = Types.SMALLINT;
			} else if (arg instanceof Integer) {
				argTypes[i] = Types.INTEGER;
			} else if (arg instanceof Long) {
				argTypes[i] = Types.BIGINT;
			} else if (arg instanceof Boolean) {
				argTypes[i] = Types.BOOLEAN;
			} else if (arg instanceof BigDecimal) {
				argTypes[i] = Types.DOUBLE;
			} else if (arg instanceof LocalDateTime) {
				argTypes[i] = Types.TIMESTAMP;
			} else {
				throw new Exception("Unknown argument class");
			}
		}
		return new SimpleImmutableEntry<>(args, argTypes);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
