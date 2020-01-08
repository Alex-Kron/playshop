package com.playshop.services;

import com.playshop.dao.ItemDAO;
import com.playshop.dao.PersonDAO;
import com.playshop.dao.PurchaseDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import org.junit.*;

import java.sql.SQLException;

public class PersonServiceImplementsTest {
    private static PersonServiceImplements anImplements;
    private static ItemDAO itemDAO;
    private static PersonDAO personDAO;
    private static PurchaseDAO purchaseDAO;
    private Item item;
    private Person person;
    private Purchase purchase;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        anImplements = new PersonServiceImplements();
        itemDAO = new ItemDAO(true);
        personDAO = new PersonDAO(true);
        purchaseDAO = new PurchaseDAO(true);
        itemDAO.deleteAll();
        personDAO.deleteAll();
        purchaseDAO.deleteAll();
    }

    @Before
    public void prepare() throws SQLException {
        item = new Item("I", "Item", 1, (float) 10);
        person = new Person("Person", "123", "user");
        purchase = new Purchase(1, 1, 2);
        itemDAO.create(item);
        personDAO.create(person);
        purchaseDAO.create(purchase);
    }

    @After
    public void clean() throws SQLException {
        itemDAO.deleteAll();
        personDAO.deleteAll();
        purchaseDAO.deleteAll();
    }

    @Test
    public void signIn() throws DBException {
        Assert.assertEquals(anImplements.signIn(person.getUsername(), person.getPassword(), true), person);
    }

    @Test
    public void signUp() throws DBException {
        Person person2 = new Person("Ben", "123", "user");
        Assert.assertEquals(anImplements.signUp(person2.getUsername(), person2.getPassword(), person2.getRole(), true), person2);
    }

    @Test
    public void getItem() throws DBException {
        Assert.assertEquals(anImplements.getItem(1, true), item);
    }

    @Test
    public void getItems() throws SQLException, DBException {
        Assert.assertEquals(anImplements.getItems(person, true), itemDAO.getAll());
    }
}