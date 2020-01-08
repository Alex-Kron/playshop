package com.playshop.dao;

import com.playshop.entity.Person;
import com.playshop.services.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private Connection connection;

    public PersonDAO() throws SQLException {
        this(false);
    }

    public PersonDAO(boolean testBase) {
        if (testBase) {
            connection = DBService.getTestConnection();
        } else {
            connection = DBService.getConnection();
        }
    }

    public Person get(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.first()) {
            return new Person(res.getString("name"), res.getString("password"), res.getString("role"));
        } else {
            return null;
        }
    }

    public List<Person> getAll() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        ArrayList<Person> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Person(res.getString("name"), res.getString("password"), res.getString("role")));
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            return null;
        }
    }

    public Person getByName(String name) throws SQLException {
        String sql = "SELECT * FROM users WHERE name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet res = statement.executeQuery();
        if (res.first()) {
            return new Person(res.getString("name"), res.getString("password"), res.getString("role"));
        } else {
            return null;
        }
    }

    public int getId(Person person) throws SQLException {
        String sql = "SELECT id FROM users WHERE name=? AND password=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getUsername());
        statement.setString(2, person.getPassword());
        ResultSet res = statement.executeQuery();
        if (res.first()) {
            return res.getInt("id");
        } else {
            return -1;
        }
    }

    public int create(Person person) throws SQLException {
        String sql = "INSERT INTO users(name, password, role) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getUsername());
        statement.setString(2, person.getPassword());
        statement.setString(3, person.getRole());
        statement.execute();
        return getId(person);
    }

    public void update(int id, Person person) throws SQLException {
        String sql = "UPDATE users SET name=?, password=?, role=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, person.getUsername());
        statement.setString(2, person.getPassword());
        statement.setString(3, person.getRole());
        statement.setInt(4, id);
        statement.execute();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM users";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        statement.execute("ALTER TABLE users AUTO_INCREMENT = 1");
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
