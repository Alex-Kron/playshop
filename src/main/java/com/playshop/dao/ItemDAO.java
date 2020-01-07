package com.playshop.dao;

import com.playshop.entity.Item;
import com.playshop.services.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection connection = DBService.getConnection();

    public ItemDAO() throws SQLException {}

    public Item get(int id) throws SQLException {
        String sql = "SELECT * FROM katalog WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if (res.first()) {
            return new Item(res.getString("name"), res.getString("description"), res.getInt("quantity"), res.getFloat("cost"));
        } else {
            return null;
        }
    }

    public int getId(Item i) throws SQLException {
        String sql = "SELECT id FROM katalog WHERE name=? AND cost=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, i.getName());
        statement.setFloat(2, i.getCost());
        ResultSet res = statement.executeQuery();
        if (res.first()) {
            return res.getInt("id");
        } else {
            return -1;
        }
    }

    public List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM katalog";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        ArrayList<Item> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Item(res.getString("name"), res.getString("description"), res.getInt("quantity"), res.getFloat("cost")));
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            return null;
        }
    }

    public int create(Item i) throws SQLException {
        String sql = "INSERT INTO katalog(quantity,name,description,cost) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, i.getQuantity());
        statement.setString(2, i.getName());
        statement.setString(3, i.getDescription());
        statement.setFloat(4, i.getCost());
        statement.execute();
        return getId(i);
    }

    public void update(int id, Item i) throws SQLException {
        String sql = "UPDATE katalog SET name=?, description=?, quantity=?, cost=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, i.getName());
        statement.setString(2, i.getDescription());
        statement.setInt(3, i.getQuantity());
        statement.setFloat(4, i.getCost());
        statement.setInt(5, id);
        statement.execute();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM katalog WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM katalog";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }
}
