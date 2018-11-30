package com.northeastern.pkotak.webdev.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleType primaryRole;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Role> role;
    private Date dateOfBirth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RoleType getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(RoleType primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void set(User newUser) {
        this.setUsername(newUser.getUsername() != null ? newUser.getUsername() : this.getUsername());
        this.setPassword(newUser.getPassword() != null ? newUser.getPassword() : this.getPassword());
        this.setFirstName(newUser.getFirstName() != null ? newUser.getFirstName() : this.getFirstName());
        this.setLastName(newUser.getLastName() != null ? newUser.getLastName() : this.getLastName());
        this.setPhone(newUser.getPhone() != null ? newUser.getPhone() : this.getPhone());
        this.setEmail(newUser.getEmail() != null ? newUser.getEmail() : this.getEmail());
        this.setRole(newUser.getRole() != null ? newUser.getRole() : this.getRole());
        this.setDateOfBirth(newUser.getDateOfBirth() != null ? newUser.getDateOfBirth() : this.getDateOfBirth());

    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
