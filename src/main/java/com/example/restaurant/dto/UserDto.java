package com.example.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    private String userLastname;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 2, max = 50, message = "Длинна имени должна быть от 2 до 50 символов!")
    private String userName;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 2, max = 50, message = "Длинна фамилии должна быть от 2 до 50 символов!")
    private String userSurname;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 11, max = 11, message = "Длинна номера телефона должна равняться 11 символам!")
    private String userPhoneNumber;
}
