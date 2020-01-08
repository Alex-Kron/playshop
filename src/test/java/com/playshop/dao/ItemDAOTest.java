package com.playshop.dao;

import com.playshop.entity.Item;
import com.playshop.services.DBService;
import org.junit.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOTest {

    private static ItemDAO dao;
    private Item item;

    @BeforeClass
    public static void prepareBase() throws SQLException {
        dao = new ItemDAO(true);
    }

    @AfterClass
    public static void closeBase() throws SQLException {
        dao.setConnection(DBService.getConnection());
    }

    @After
    public void clearBase() throws SQLException {
        Statement s = DBService.getTestConnection().createStatement();
        s.execute("DELETE FROM katalog");
    }

    @Before
    public void prepare() throws SQLException {
        item = new Item("A", "Alpha", 1, (float) 10.00);
        PreparedStatement statement = DBService.getTestConnection().prepareStatement("INSERT INTO katalog(quantity, name, description, cost) VALUES(?,?,?,?)");
        statement.setInt(1, item.getQuantity());
        statement.setString(2, item.getName());
        statement.setString(3, item.getDescription());
        statement.setFloat(4, item.getCost());
        statement.execute("ALTER TABLE katalog AUTO_INCREMENT = 1");
        statement.execute();
    }

    @Test
    public void get() throws SQLException {
        Assert.assertEquals(dao.get(1), item);
    }

    @Test
    public void getId() throws SQLException {
        Assert.assertEquals(dao.getId(item), 1);
        Assert.assertEquals(dao.getId(new Item("B", "Betta", 10, (float) 10.00)), -1);
    }

    @Test
    public void getAll() throws SQLException {
        List<Item> list = new ArrayList<>();
        list.add(item);
        Assert.assertEquals(dao.getAll(), list);
    }

    @Test
    public void create() throws SQLException {
        Assert.assertEquals(dao.create(new Item("B", "Betta", 20, (float) 5.00)), 2);
    }

    @Test
    public void update() throws SQLException {
        Item item2 = new Item("B", "Betta", 10, (float) 10.00);
        dao.update(1,item2);
        Assert.assertEquals(dao.get(1), item2);
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