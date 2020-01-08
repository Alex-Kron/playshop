package com.playshop.services;

import com.playshop.dao.ItemDAO;
import com.playshop.dao.PersonDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.exceptions.DBException;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PersonServiceImplements implements PersonService {

    private static final Logger logger = Logger.getLogger(PersonServiceImplements.class.getName());

    @Override
    public Person signIn(String name, String password) throws DBException {
        return signIn(name, password, false);
    }

    public Person signIn(String name, String password, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            Person person = dao.getByName(name);
            if (person == null || !person.getPassword().equals(password)){
                return null;
            }
            logger.fine("Person signed: " + person);
            return person;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Person signUp(String name, String password) throws DBException {
        return this.signUp(name, password, "user");
    }

    @Override
    public Person signUp(String name, String password, String role) throws DBException {
        return signUp(name, password, role, false);
    }

    public Person signUp(String name, String password, String role, boolean test) throws DBException {
        try {
            PersonDAO dao = new PersonDAO(test);
            int id = dao.create(new Person(name, password, role));
            Person person = dao.get(id);
            logger.fine("Person registered: " + person);
            return person;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Item getItem(int id) throws DBException {
        return getItem(id, false);
    }

    public Item getItem(int id, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            Item item = dao.get(id);
            return item;

        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Item> getItems(Person person) throws DBException {
        return getItems(person, false);
    }

    public List<Item> getItems(Person person, boolean test) throws DBException {
        try {
            ItemDAO dao = new ItemDAO(test);
            List<Item> items = dao.getAll();
            if (person.getRole() == "admin"){
                items.sort(Comparator.comparing(Item::getName));
            } else {
                items = items.stream()
                        .filter((item) -> item.getQuantity() > 0)
                        .sorted(Comparator.comparing(Item::getName))
                        .collect(Collectors.toList());
            }
            return items;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}