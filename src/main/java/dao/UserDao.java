package dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import entity.User;

@Component
public class UserDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// private static UserDao userdao=new UserDao();

	// public static UserDao getDao() {
	//
	// }
	// public UserDao(){
	// this.jdbcTemplate=new JdbcTemplate(this.getDataSource());
	// }

//	@Autowired
//	public UserDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
//	public UserDao() {
//
//	}

	// public JdbcTemplate getMYJdbcTemplate() {
	// if (jdbcTemplate == null) {
	// jdbcTemplate = new JdbcTemplate(dataSource);
	// }
	// return jdbcTemplate;
	// }
	//
//	@Bean
	// @ConfigurationProperties(prefix="spring.datasource")
	// public DataSource getDataSource() {
	// // dataSource=new BasicDataSource();
	// dataSource=DataSourceBuilder.create().build();
	// System.out.print("dataSource:---------------"+dataSource.getClass());
	// return dataSource ;
	// }
	//
	// public void setDataSource(BasicDataSource dataSource) {
	// this.dataSource = dataSource;
	// }
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<User> getUserById(String id, int begin, int end) {
		List<User> personls = null;
		try {
			personls = (List<User>) jdbcTemplate
					.query("select * from users where id=? order by id desc limit ?,?",
							new Object[] { id, begin, end }, new int[] {
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER },
							new UserRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return personls;
	}

	public List<User> getUserByName(String name, int begin, int end) {
		List<User> personLs = null;
		try {
			personLs = (List<User>) jdbcTemplate
					.query("select * from users where name like ? order by id desc limit ?,? ",
							new Object[] { '%' + name + '%', begin, end },
							new int[] { java.sql.Types.VARCHAR,
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER },
							new UserRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return personLs;
	}

	public String getUserNameById(String id) {
		String username = null;
		try {
			username = jdbcTemplate.queryForObject(
					"select name from users where id=?", new Object[] { id },
					new int[] { java.sql.Types.INTEGER }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return username;
	}

	public List<User> getUserByNameId(String name, String id, int begin, int end) {
		List<User> personLs = null;
		try {
			personLs = (List<User>) jdbcTemplate
					.query("select * from users where name like ? and id=? order by id desc limit ?,? ",
							new Object[] { '%' + name + '%', id, begin, end },
							new int[] { java.sql.Types.VARCHAR,
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER,
									java.sql.Types.INTEGER },
							new UserRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return personLs;
	}

	public List<User> getAllUser(int begin, int end) {
		List<User> list = null;
		try {
			list = jdbcTemplate.query(
					"select * from users order by id desc limit ?,?",
					new Object[] { begin, end }, new int[] {
							java.sql.Types.INTEGER, java.sql.Types.INTEGER },
					new UserRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return list;
	}

	public int getUserCount() {
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt("select count(id) from users");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getUserByIdCount(String id) {
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt(
					"select count(id) from users where id=?",
					new Object[] { id }, new int[] { java.sql.Types.INTEGER });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getUserByNameCount(String name) {
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt(
					"select count(id) from users where name like ?",
					new Object[] { '%' + name + '%' },
					new int[] { java.sql.Types.VARCHAR });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int getUserByNameIdCount(String name, String id) {
		int count = 0;
		try {
			count = jdbcTemplate.queryForInt(
					"select count(id) from users where name like ? and id=?",
					new Object[] { '%' + name + '%', id }, new int[] {
							java.sql.Types.VARCHAR, java.sql.Types.INTEGER });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public boolean saveUser(User user) {
		try {
			jdbcTemplate
					.update("insert into users(name,passwd)values(?,?)",
							new Object[] { user.getName(), user.getPassword() },
							new int[] { java.sql.Types.VARCHAR,
									java.sql.Types.VARCHAR });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean deleteUser(String id) {
		try {
			jdbcTemplate.update("delete from users where id=?",
					new Object[] { id }, new int[] { java.sql.Types.INTEGER });

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateUser(User user) {
		try {
			jdbcTemplate.update(
					"update users set name=?,passwd=? where id=?",
					new Object[] { user.getName(), user.getPassword(),
							user.getId() }, new int[] { java.sql.Types.VARCHAR,
							java.sql.Types.VARCHAR, java.sql.Types.INTEGER });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
