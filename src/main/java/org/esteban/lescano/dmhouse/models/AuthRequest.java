package org.esteban.lescano.dmhouse.models;


public class AuthRequest {
    private String fullname;
    private String password;

    public AuthRequest() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
