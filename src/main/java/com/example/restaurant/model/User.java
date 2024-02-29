package com.example.restaurant.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Transient;

@Data
public class User {
    private Long userId;
    private String userRole;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 2, max = 50, message = "Длинна имени должна быть от 2 до 50 символов!")
    private String userName;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 2, max = 50, message = "Длинна фамилии должна быть от 2 до 50 символов!")
    private String userSurname;

    private String userLastname;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 11, max = 11, message = "Длинна номера телефона должна равняться 11 символам!")
    private String userPhoneNumber;

    @NotBlank(message = "Обязательное для заполнения поле!")
    @Size(min = 5, max = 20, message = "Длинна пароля должна быть от 5 до 20 символов!")
    private String userPassword;

    @Transient
    private String userPasswordConfirm;
}
