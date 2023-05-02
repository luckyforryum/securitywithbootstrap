package ru.kata.spring.boot_security.demo.dto;
import java.util.Objects;

public class LoginDto {

    private String username;
    private String email;
    private String password;

    public LoginDto() {

    }

    public LoginDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDto loginDto = (LoginDto) o;
        return Objects.equals(username, loginDto.username) && Objects.equals(email, loginDto.email) && Objects.equals(password, loginDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
