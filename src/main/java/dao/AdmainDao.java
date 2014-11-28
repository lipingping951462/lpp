package dao;

import javax.sql.DataSource;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import entity.Admin;
import entity.User;

public class AdmainDao {
	private DataSource dataSource = null;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean validAdmin(String name, String password) {
		String sql = "select count(name) from admin where name=? &password=?";
		try {
			int count = this.jdbcTemplate.queryForInt(sql, new Object[] { name,
					password }, new int[] { java.sql.Types.VARCHAR,
					java.sql.Types.VARCHAR });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public Admin getAdmin(String name, String password) {
		String sql = "select name,password from admin where name=? and password=?";
		Admin admin=null;
		try {
			 admin = this.jdbcTemplate.queryForObject(sql, new Object[] { name,
					password }, new int[] { java.sql.Types.VARCHAR,
					java.sql.Types.VARCHAR },new AdminRowMapper() {
					});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return admin;
	}
}
