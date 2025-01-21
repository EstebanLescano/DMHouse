package org.esteban.userservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    @Column(name = "dni", unique = true, nullable = false)
    private String dni; // Documento único

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "type_document_id")
    private Integer typeDocumentId;


    @Column(name = "birthdate")
    private Date birthdate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Client client;

    public Person() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer personId) {
        this.Id = personId;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getTypeDocumentId() {
        return typeDocumentId;
    }

    public void setTypeDocumentId(Integer typeDocumentId) {
        this.typeDocumentId = typeDocumentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
