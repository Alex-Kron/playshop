package com.playshop.entity;

public class Person {

    private String username;
    private String password;
    private String role;

    public Person(String n, String p, String a) {
        username = n;
        password = p;
        role = a;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {return role;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
