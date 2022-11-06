package com.example.academicdraft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "Организационно-правовая форма должна быть введёна")
    private String type;

    @NotEmpty(message = "Наименование должно быть введёно")
    private String name;

    @NotEmpty(message = "E-mail должен быть введён")
    @Email
    private String email;

    @NotEmpty(message = "Пароль должен быть введён")
    private String password;

}
