package com.playshop.dao;

import com.playshop.entity.Person;
import com.playshop.services.DBService;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOTest {

    private static PersonDAO dao;
    private Person person;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        dao = new PersonDAO(true);
    }

    @AfterClass
    public static void closeBase() throws SQLException {
        dao.setConnection(DBService.getConnection());
    }

    @After
    public void clearBase() throws SQLException {
        Statement s = DBService.getTestConnection().createStatement();
        s.execute("DELETE FROM users");
    }

    @Before
    public void prepare() throws SQLException {
        person = new Person("A", "123", "user");
        PreparedStatement statement = DBService.getTestConnection().prepareStatement("INSERT INTO users(name, password, role) VALUES(?,?,?)");
        statement.setString(1, person.getUsername());
        statement.setString(2, person.getPassword());
        statement.setString(3, person.getRole());
        statement.execute("ALTER TABLE users AUTO_INCREMENT = 1");
        statement.execute();
    }

    @Test
    public void get() throws SQLException {
        Assert.assertEquals(dao.get(1), person);
    }

    @Test
    public void getAll() throws SQLException {
        List<Person> list = new ArrayList<>();
        list.add(person);
        Assert.assertEquals(dao.getAll(), list);
    }

    @Test
    public void getByName() throws SQLException {
        Assert.assertEquals(dao.getByName(person.getUsername()), person);
    }

    @Test
    public void getId() throws SQLException {
        Assert.assertEquals(dao.getId(person), 1);
        Assert.assertEquals(dao.getId(new Person("B", "123", "user")), -1);
    }

    @Test
    public void create() throws SQLException {
        Assert.assertEquals(dao.create(new Person("B", "123", "user")), 2);
    }

    @Test
    public void update() throws SQLException {
        Person person2 = new Person("B", "123", "user");
        dao.update(1, person2);
        Assert.assertEquals(dao.get(1), person2);
    }

    @Test
    public void delete() throws SQLException {
        dao.delete(1);
        Assert.assertNull(dao.get(1));
    }

    @Test
    public void deleteAll() throws SQLException {
        dao.deleteAll();
        Assert.assertNull(dao.getAll());
    }
}