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
        return createItem(item, false);
    }

    public int createItem(Item item, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            int id = dao.create(item);
            logger.fine("Create item " + item);
            return id;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateItem(int id, Item item) throws DBException {
        updateItem(id, item, false);
    }

    public void updateItem(int id, Item item, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            dao.update(id, item);
            logger.fine("Update item " + item);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public int getItemId(Item item) throws DBException {
        return getItemId(item, false);
    }

    public int getItemId(Item item, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            int id = dao.getId(item);
            logger.fine("Get item id " + id + "for item" + item);
            return id;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteItem(int id) throws DBException {
        deleteItem(id, false);
    }

    public void deleteItem(int id, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            Item item = dao.get(id);
            dao.delete(id);
            logger.fine("Delete item " + item);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllItems() throws DBException {
        deleteAllItems(false);
    }

    public void deleteAllItems(boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            dao.deleteAll();
            logger.fine("Delete all items");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllPersons() throws DBException {
        deleteAllPersons(false);
    }

    public void deleteAllPersons(boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            dao.deleteAll();
            logger.fine("Delete all persons");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void setAdmin(int id) throws DBException {
        setAdmin(id, false);
    }
    public void setAdmin(int id, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            Person person = dao.get(id);
            person.setRole("admin");
            dao.update(id, person);
            logger.fine("Set user " + id + " - admin");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void setUser(int id) throws DBException {
        setUser(id, false);
    }
    public void setUser(int id, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            Person person = dao.get(id);
            person.setRole("user");
            dao.update(id, person);
            logger.fine("Set user " + id + " - admin");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteAllPurchases() throws DBException {
        deleteAllPurchases(false);
    }

    public void deleteAllPurchases(boolean test) throws DBException {
        try {
            PurchaseDAO dao = new PurchaseDAO(test);
            dao.deleteAll();
            logger.fine("Delete all purchases");
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Purchase> getPurchases() throws DBException {
        return getPurchases(false);
    }

    public List<Purchase> getPurchases(boolean test) throws DBException {
        try {
            PurchaseDAO dao = new PurchaseDAO(test);
            return dao.getAll();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Person> getAllPerson() throws DBException {
        return getAllPerson(false);
    }

    public List<Person> getAllPerson(boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            return dao.getAll();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deletePerson(int id) throws DBException {
        deletePerson(id, false);
    }

    public void deletePerson(int id, boolean test) throws DBException {
        PersonDAO dao = null;
        try {
            dao = new PersonDAO(test);
            dao.delete(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public int getPersonId(String name) throws DBException {
        return getPersonId(name, false);
    }

    public int getPersonId(String name, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            return dao.getId(dao.getByName(name));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Person getPerson(int id) throws DBException {
        return getPerson(id, false);
    }

    public Person getPerson(int id, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            return dao.get(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
