package com.example.restaurant.repository.mapper;

import com.example.restaurant.dto.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoMapper implements RowMapper<UserDto> {

    @Override
    public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setUserPhoneNumber(rs.getString("user_phone"));
        userDto.setUserName(rs.getString("user_name"));
        userDto.setUserSurname(rs.getString("user_surname"));
        userDto.setUserLastname(rs.getString("user_lastname"));
        return userDto;
    }
}
