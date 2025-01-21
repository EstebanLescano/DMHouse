package org.esteban.userservice.models;

import java.util.Date;

public class ClientDTO {
    private Integer clientId;
    private String name;
    private String lastName;
    private String DNI;
    private String email;
    private Integer phone;
    private Date dateLogin;

    public ClientDTO() {
    }

    public ClientDTO(String string, String name, String lastName, String email, String dni, Integer phone, String cvu, String alias) {
        this.name = name;
        this.lastName = lastName;
        this.DNI = dni;
        this.email = email;
        this.phone = phone;
        this.dateLogin = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
