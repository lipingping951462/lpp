package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.Admin;

public class AdminRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet set, int rowNum) throws SQLException {
		if(null==set||set.wasNull()){
			return null;
		}
		Admin admin=new Admin(set.getString("name"),set.getString("password"));
		return admin;
	}

}
