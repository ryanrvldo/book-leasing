package com.lawencon.bookleasing.dao.jdbc.basic;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Rian Rivaldo Rumapea
 */
class BaseDaoImpl {

	private Connection connection;

	public void setDataSource(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {
		return this.connection;
	}

}
