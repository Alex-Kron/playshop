package com.playshop.dao;

import com.playshop.entity.Purchase;
import com.playshop.services.DBService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    private Statement statement = DBService.getStatement();

    public PurchaseDAO() throws SQLException {}

    public Purchase get(int id) throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM purchase WHERE id=" + id);
        return new Purchase(res.getInt("user_id"), res.getInt("item_id"), res.getInt("quantity"), res.getFloat("cost"));
    }

    public List<Purchase> getAll() throws SQLException {
        ResultSet res = statement.executeQuery("SELECT * FROM purchase");
        ArrayList<Purchase> list = new ArrayList<>();
        while (!res.isAfterLast()) {
            list.add(new Purchase(res.getInt("user_id"), res.getInt("item_id"), res.getInt("quantity"), res.getFloat("cost")));
            res.next();
        }
        return list;
    }

    public void create(Purchase purchase) throws SQLException {
        statement.execute("INSERT INTO purchase(user_id, item_id, quantity, cost) VALUES(" + purchase.getUserID() + ", " + purchase.getItemID() + ", " + purchase.getQuantity() + ", " + purchase.getCost() + ")");
    }

    public void delete(int id) throws SQLException {
        statement.execute("DELETE FROM purchase WHERE id=" + id);
    }

    public void deleteAll() throws SQLException {
        statement.execute("DELETE FROM purchase");
    }
}
