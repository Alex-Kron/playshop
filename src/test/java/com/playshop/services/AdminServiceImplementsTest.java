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

public class AdminServiceImplementsTest {

    private static AdminServiceImplements anImplements;
    private static ItemDAO itemDAO;
    private static PersonDAO personDAO;
    private static PurchaseDAO purchaseDAO;
    private Item item;
    private Person person;
    private Purchase purchase;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        anImplements = new AdminServiceImplements();
        itemDAO = new ItemDAO(true);
        personDAO = new PersonDAO(true);
        purchaseDAO = new PurchaseDAO(true);
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
    public void createItem() throws DBException {
        int i = anImplements.createItem(new Item("B", "Betta", 2, 10), true);
        Assert.assertEquals(i, 2);
    }

    @Test
    public void updateItem() throws DBException, SQLException {
        Item item2 = new Item("B", "Betta", 2, 10);
        anImplements.updateItem(1, item2, true);
        Assert.assertEquals(itemDAO.get(1), item2);
    }

    @Test
    public void getItemId() throws DBException {
        Item item2 = new Item("B", "Betta", 2, 10);
        Assert.assertEquals(anImplements.getItemId(item, true), 1);
        Assert.assertEquals(anImplements.getItemId(item2, true), -1);
    }

    @Test
    public void deleteItem() throws DBException, SQLException {
        anImplements.deleteItem(1, true);
        Assert.assertNull(itemDAO.get(1));
    }

    @Test
    public void deleteAllItems() throws DBException, SQLException {
        anImplements.deleteAllItems(true);
        Assert.assertNull(itemDAO.getAll());
    }

    @Test
    public void deleteAllPersons() throws DBException, SQLException {
        anImplements.deleteAllPersons(true);
        Assert.assertNull(personDAO.getAll());
    }

    @Test
    public void setAdmin() throws DBException, SQLException {
        anImplements.setAdmin(1, true);
        Assert.assertEquals(personDAO.get(1).getRole(), "admin");
    }

    @Test
    public void deleteAllPurchases() throws DBException, SQLException {
        anImplements.deleteAllPurchases(true);
        Assert.assertNull(purchaseDAO.getAll());
    }

    @Test
    public void getPurchases() throws DBException, SQLException {
        Assert.assertEquals(anImplements.getPurchases(true), purchaseDAO.getAll());
    }
}