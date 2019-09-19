package com.house.model;

import com.house.validation.ValidEmail;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull(message = "{message.validation.name.empty}")
    @Column(name= "name")
    @Length(min = 5,max = 100,message = "{message.validation.name.length}")
    private String name;
    @NotNull
    @Column(name = "email")
    @ValidEmail
    private String email;
    @Column(name = "password")
    private String password;
    private String role;
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean isDisabled;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public List<String> getRoleList(){
        if(this.role.length()>0){
            return Arrays.asList(this.role.split(","));
        }else{
            return new ArrayList<>();
        }
    }
}
