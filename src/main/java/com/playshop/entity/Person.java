package com.playshop.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getUsername().equals(person.getUsername()) &&
                getPassword().equals(person.getPassword()) &&
                getRole().equals(person.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRole());
    }
}
