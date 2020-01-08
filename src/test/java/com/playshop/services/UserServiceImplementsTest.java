package com.playshop.services;

import com.playshop.dao.ItemDAO;
import com.playshop.dao.PersonDAO;
import com.playshop.dao.PurchaseDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import com.playshop.exceptions.ServiceException;
import org.junit.*;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserServiceImplementsTest {

    private static UserServiceImplements anImplements;
    private static ItemDAO itemDAO;
    private static PersonDAO personDAO;
    private static PurchaseDAO purchaseDAO;
    private Item item;
    private Person person;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        anImplements = new UserServiceImplements();
        itemDAO = new ItemDAO(true);
        personDAO = new PersonDAO(true);
        purchaseDAO = new PurchaseDAO(true);
        itemDAO.deleteAll();
        personDAO.deleteAll();
        purchaseDAO.deleteAll();
    }

    @Before
    public void prepare() throws SQLException {
        item = new Item("I", "Item", 2, (float) 10);
        person = new Person("Person", "123", "user");
        itemDAO.create(item);
        personDAO.create(person);
    }

    @After
    public void clean() throws SQLException {
        itemDAO.deleteAll();
        personDAO.deleteAll();
        purchaseDAO.deleteAll();
    }

    @Test
    public void setPurchase() throws SQLException, DBException, ServiceException {
        Purchase purchase = new Purchase(1,1,1);
        anImplements.setPurchase(1,1, person, true);
        Assert.assertEquals(purchaseDAO.get(1), purchase);
        Assert.assertEquals(itemDAO.get(1).getQuantity(), 1);
    }
}