package fr.biblioc.bibliocclientUi.beans.utilities;

import javax.validation.constraints.NotNull;

public class UserConnexion {

    @NotNull
    private String email;

    @NotNull
    private String password;

    public UserConnexion() {
    }

    public UserConnexion(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserConnexion{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
