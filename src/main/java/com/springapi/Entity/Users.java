package com.springapi.Entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedDate = new Date(System.currentTimeMillis());

    public Users(){

    }

    public Users(String email,String password){
        this.email=email;
        this.password=password;
    }
    public Users(int id,String name,String email,String password,String phoneNumber){
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;

    }
    public Users(String name,String email,String password,String phoneNumber){
        this.name=name;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
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

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    @Override
    public int hashCode() {
        int hash=7;
        return id*hash + email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        Users other = (Users) obj;
        if (other.id == this.id&& Objects.equals(other.email, this.email)) return true;
        return false;
    }
}
