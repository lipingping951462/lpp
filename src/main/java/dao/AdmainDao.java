package dao;

import javax.sql.DataSource;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
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
		String sql = "select count(name) from admin where name=? password=?";
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
}
