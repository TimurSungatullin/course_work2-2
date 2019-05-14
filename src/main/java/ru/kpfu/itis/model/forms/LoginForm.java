package ru.kpfu.itis.model.forms;

import java.util.Objects;

public class LoginForm {

    private String email;
    private String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginForm)) return false;
        LoginForm loginForm = (LoginForm) o;
        return Objects.equals(getEmail(), loginForm.getEmail()) &&
                Objects.equals(getPassword(), loginForm.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
