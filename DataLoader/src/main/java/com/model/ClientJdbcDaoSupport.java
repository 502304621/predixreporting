
package com.model;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;



public class ClientJdbcDaoSupport extends JdbcDaoSupport {

	public JdbcTemplate getJdbcTemplate(String dataSourceName, String userName, String password) {


		DataSource dataSource = getDataSource(userName, password, dataSourceName);

		setJdbcTemplate(new JdbcTemplate(dataSource));

		return getJdbcTemplate();
	}

	private DataSource getDataSource(String username, String password,
			String url) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		return dataSource;
	}
}