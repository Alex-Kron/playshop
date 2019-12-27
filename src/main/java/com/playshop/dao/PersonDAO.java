package com.playshop.dao;

import com.playshop.entity.Person;
import com.playshop.services.DBService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private Statement statement = DBService.getStatement();

    public PersonDAO() throws SQLException {}

    public Person get(int id) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM users WHERE id=" + id);
        return new Person(res.getString("name"), res.getString("password"), res.getString("role"));
    }

    public List<Person> getAll() throws SQLException {
        ResultSet res = statement.executeQuery("SELECT *FROM users");
        ArrayList<Person> list = new ArrayList<>();
        while (!res.isAfterLast()) {
            list.add(new Person(res.getString("name"), res.getString("password"), res.getString("role")));
            res.next();
        }
        return list;
    }

    public Person getByName(String name) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM users WHERE name=" + name);
        return new Person(res.getString("name"), res.getString("password"), res.getString("role"));
    }

    public int create(Person person) throws SQLException {
        statement.execute("INSERT INTO users(name, password, role) VALUES (" + person.getUsername() + ", " + person.getPassword() + ", " + person.getRole() + ")");
        ResultSet res = statement.executeQuery("SELECT id FROM users WHERE name=" + person.getUsername());
        return res.getInt("id");
    }

    public void delete(int id) throws SQLException {
        statement.execute("DELETE FROM users WHERE id=" + id);
    }

    public void deleteAll() throws SQLException {
        statement.execute("DELETE FROM users");
    }

}
