package com.business.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user_profile")
public class UserProfile {
    @Id
    private String _id;

    private String commonName;

    private String firstName;

    private String lastName;

    private String middleName;

    private String email;

    private String cnum;

    private String photo;

    private String country;

    @JsonIgnore
    private String password;

    private List<String> roles;

    public UserProfile() {
    }

    public UserProfile(String _id, String commonName, String firstName, String lastName, String middleName,
                       String email, String cnum, String photo, String country, String password, List<String> roles) {
        this._id = _id;
        this.commonName = commonName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.cnum = cnum;
        this.photo = photo;
        this.country = country;
        this.password = password;
        this.roles = roles;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "_id=" + _id +
                ", commonName='" + commonName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", cnum='" + cnum + '\'' +
                ", photo='" + photo + '\'' +
                ", country='" + country + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
