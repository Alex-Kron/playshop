package com.playshop.dao;

import com.playshop.entity.Item;
import com.playshop.services.DBService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Statement statement = DBService.getStatement();

    public ItemDAO() throws SQLException {}

    public Item get(int id) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM katalog WHERE id=" + id);
        return new Item(res.getString("name"), res.getString("description"), res.getInt("quantity"), res.getFloat("cost"));
    }

    public int getId(Item i) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT id FROM katalog WHERE name=" + i.getName() + " AND cost=" + i.getCost());
        return res.getInt("id");
    }

    public List<Item> getAll() throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM katalog");
        ArrayList<Item> list = new ArrayList<>();
        while (!res.isAfterLast()) {
            list.add(new Item(res.getString("name"), res.getString("description"), res.getInt("quantity"), res.getFloat("cost")));
            res.next();
        }
        return list;
    }

    public int create(Item i) throws SQLException {
        statement.execute("INSERT INTO katalog(quantity,name,description,cost) VALUES(" + i.getQuantity() + ", " + i.getName() + ", " + i.getDescription() + ", " + i.getCost() + ")");
        ResultSet res = statement.executeQuery("SELECT id FROM katalog WHERE name=" + i.getName() + " AND cost=" + i.getCost());
        return res.getInt("id");
    }

    public void update(int id, Item i) throws SQLException {
        statement.execute("UPDATE katalog SET name=" + i.getName() + ", description=" + i.getDescription() + ", quantity =" + i.getQuantity() + ", cost =" + i.getCost() + " WHERE id=" + id);
    }

    public void delete(int id) throws SQLException {
        statement.execute("DELETE FROM katalog WHERE id=" + id);
    }

    public void deleteAll() throws SQLException {
        statement.execute("DELETE FROM katalog");
    }
}
