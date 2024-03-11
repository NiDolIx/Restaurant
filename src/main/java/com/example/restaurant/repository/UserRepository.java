package com.example.restaurant.repository;

import com.example.restaurant.dto.UserDto;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.mapper.UserDtoMapper;
import com.example.restaurant.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void updateUser(UserDto userDto, Long userId) {
        String sql = """
                UPDATE public.user
                SET
                    user_name = :user_name,
                    user_surname = :user_surname,
                    user_lastname = :user_lastname,
                    user_phone = :user_phone
                WHERE user_id = :user_id
                """;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_name", userDto.getUserName())
                .addValue("user_surname", userDto.getUserSurname())
                .addValue("user_lastname", userDto.getUserLastname())
                .addValue("user_phone", userDto.getUserPhoneNumber())
                .addValue("user_id", userId);
        template.update(sql, parameters);
    }

    public Optional<UserDto> findUserById(Long userId) {
        String sql = """
                SELECT
                    user_name, 
                    user_surname, 
                    user_lastname, 
                    user_phone
                FROM public.user
                WHERE user_id = ?
                """;
        return template.getJdbcTemplate()
                .query(sql, new UserDtoMapper(), userId)
                .stream()
                .findAny();
    }

    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        String sql = """
                SELECT 
                    user_id, 
                    user_name, 
                    user_surname, 
                    user_lastname, 
                    user_role, 
                    user_phone, 
                    user_password  
                FROM public.user
                WHERE user_phone = ?
                """;
        return template.getJdbcTemplate()
                .query(sql, new UserMapper(), phoneNumber)
                .stream()
                .findAny();
    }

    public void insertUser(User user) {
        String sql = """
                INSERT INTO public.user(user_role, user_name, user_surname, user_lastname, user_phone, user_password)
                VALUES (:user_role, :user_name, :user_surname, :user_lastname, :user_phone, :user_password)
                """;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("user_role", user.getUserRole())
                .addValue("user_name", user.getUserName())
                .addValue("user_surname", user.getUserSurname())
                .addValue("user_lastname", user.getUserLastname())
                .addValue("user_phone", user.getUserPhoneNumber())
                .addValue("user_password", user.getUserPassword());
        template.update(sql, parameters);
    }
}
