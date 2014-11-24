package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.User;

public class UserRowMapper implements RowMapper{

	public User mapRow(ResultSet set, int arg1) throws SQLException {

		if(null==set||set.wasNull()){
			return null;
		}
		User user=new User(set.getString("id"),set.getString("name"),set.getString("passwd"));
		return user;
	}

}
