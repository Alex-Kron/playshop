package com.playshop.services;

import com.playshop.dao.ItemDAO;
import com.playshop.dao.PersonDAO;
import com.playshop.dao.PurchaseDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class AdminServiceImplements extends PersonServiceImplements implements AdminService {

    private static final Logger logger = Logger.getLogger(AdminServiceImplements.class.getName());

    @Override
    public int createItem(Item item) throws DBException {
        try {
            ItemDAO dao = new ItemDAO();
            int id = dao.create(item);
            logger.fine("Create item " + item);
            return id;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateItem(int id, Item item) throws DBException {
        try {
            ItemDAO dao = new ItemDAO();
            dao.update(id, item);
            logger.fine("Update item " + item);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public int getItemId(Item item) throws DBException {
        try {
            ItemDAO dao = new ItemDAO();
            int id = dao.getId(item);
            logger.fine("Get item id " + id + "for item" + item);
            return id;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteItem(int id) throws DBException {
        try {
            ItemDAO dao = new ItemDAO();
            Item item = dao.get(id);
            dao.delete(id);
            logger.fine("Delete item " + item);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllItems() throws DBException {
        try {
            ItemDAO dao = new ItemDAO();
            dao.deleteAll();
            logger.fine("Delete all items");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllPersons() throws DBException {
        try {
            PersonDAO dao = new PersonDAO();
            dao.deleteAll();
            logger.fine("Delete all persons");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void setAdmin(int id) throws DBException {
        try {
            PersonDAO dao = new PersonDAO();
            Person person = dao.get(id);
            person.setRole("admin");
            dao.update(id, person);
            logger.fine("Set user " + id + " - admin");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllPurchases() throws DBException {
        try {
            PurchaseDAO dao = new PurchaseDAO();
            dao.deleteAll();
            logger.fine("Delete all purchases");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Purchase> getPurchases() throws DBException {
        try {
            PurchaseDAO dao = new PurchaseDAO();
            return dao.getAll();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
