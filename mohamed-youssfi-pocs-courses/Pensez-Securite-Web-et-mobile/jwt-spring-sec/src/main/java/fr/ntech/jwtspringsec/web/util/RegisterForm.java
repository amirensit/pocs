package fr.ntech.jwtspringsec.web.util;

import lombok.Data;

@Data
public class RegisterForm {

    private String username;
    private String password;
    private String repassword;
}
