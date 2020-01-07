package com.playshop.dao;

import com.playshop.entity.Purchase;
import com.playshop.services.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    private Connection connection = DBService.getConnection();

    public PurchaseDAO() throws SQLException {}

    public Purchase get(int id) throws SQLException {
        String sql = "SELECT * FROM purchase WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        res.first();
        return new Purchase(res.getInt("user_id"), res.getInt("item_id"), res.getInt("quantity"));
    }

    public List<Purchase> getAll() throws SQLException {
        String sql = "SELECT * FROM purchase";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        ArrayList<Purchase> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Purchase(res.getInt("user_id"), res.getInt("item_id"), res.getInt("quantity")));
        }
        return list;
    }

    public int getId(Purchase purchase) throws SQLException {
        String sql = "SELECT id FROM purchase WHERE user_id=? AND item_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, purchase.getUserID());
        statement.setInt(2, purchase.getItemID());
        ResultSet res = statement.executeQuery();
        res.first();
        return res.getInt("id");
    }

    public int create(Purchase purchase) throws SQLException {
        String sql = "INSERT INTO purchase(user_id, item_id, quantity, cost) VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        statement.setInt(1,purchase.getUserID());
        statement.setInt(2,purchase.getItemID());
        statement.setInt(3,purchase.getQuantity());
        ItemDAO tmp = new ItemDAO();
        float cost = tmp.get(purchase.getItemID()).getCost() * purchase.getQuantity();
        statement.setFloat(4, cost);
        return getId(purchase);
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM purchase WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }

    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM purchase";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
    }
}
