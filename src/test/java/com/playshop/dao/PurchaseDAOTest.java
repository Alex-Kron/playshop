package com.playshop.dao;

import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.services.DBService;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAOTest {

    private static PurchaseDAO dao;
    private Purchase purchase;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        dao = new PurchaseDAO(true);
    }

    @AfterClass
    public static void closeBase() throws SQLException {
        dao.setConnection(DBService.getConnection());
    }

    @After
    public void clearBase() throws SQLException {
        Statement s = DBService.getTestConnection().createStatement();
        s.execute("DELETE FROM purchase");
        s.execute("DELETE FROM katalog");
    }

    @Before
    public void prepare() throws SQLException {
        purchase = new Purchase(1, 1, 2);
        PreparedStatement statement = DBService.getTestConnection().prepareStatement("INSERT INTO purchase(user_id, item_id, quantity, cost) VALUES(?,?,?,?)");
        statement.setInt(1,purchase.getUserID());
        statement.setInt(2, purchase.getItemID());
        statement.setInt(3, purchase.getQuantity());
        Statement tmp = DBService.getTestConnection().createStatement();
        tmp.execute("INSERT INTO katalog(quantity, name, description, cost) VALUES (10, 'A', 'Alpha', 10.00)");
        statement.setFloat(4, (float) 20.00);
        statement.execute("ALTER TABLE purchase AUTO_INCREMENT = 1");
        statement.execute();
    }

    @Test
    public void get() throws SQLException {
        Assert.assertEquals(dao.get(1), purchase);
    }

    @Test
    public void getAll() throws SQLException {
        List<Purchase> list = new ArrayList<>();
        list.add(purchase);
        Assert.assertEquals(dao.getAll(), list);
    }

    @Test
    public void getId() throws SQLException {
        Assert.assertEquals(dao.getId(purchase), 1);
        Assert.assertEquals(dao.getId(new Purchase(2, 2,2)), -1);
    }

    @Test
    public void create() throws SQLException {
        Assert.assertEquals(dao.create(new Purchase(2,2,2)), 2);
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