package com.example.restaurant.repository.mapper;

import com.example.restaurant.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setUserSurname(rs.getString("user_surname"));
        user.setUserLastname(rs.getString("user_lastname"));
        user.setUserRole(rs.getString("user_role"));
        user.setUserPhoneNumber(rs.getString("user_phone"));
        user.setUserPassword(rs.getString("user_password"));
        return user;
    }
}
