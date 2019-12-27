package com.playshop.entity;

public class Person {

    private String username;
    private String password;
    private String email;
    private boolean admin;

    public Person(String n, String p, String e) {
        username = n;
        password = p;
        email = e;
        admin = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }
}
