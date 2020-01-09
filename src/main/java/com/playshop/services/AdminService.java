package com.playshop.services;

import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import java.util.List;

public interface AdminService extends PersonService {

    int createItem(Item item) throws DBException;

    int getItemId(Item item) throws DBException;

    int getPersonId(String name) throws DBException;

    void updateItem(int id, Item item) throws DBException;

    void deleteItem(int id) throws DBException;

    void deleteAllItems() throws DBException;

    void deletePerson(int id) throws DBException;

    void deleteAllPersons() throws DBException;

    void setAdmin(int id) throws DBException;

    void setUser(int id) throws DBException;

    Person getPerson(int id) throws DBException;

    void deleteAllPurchases() throws DBException;

    List<Person> getAllPerson() throws DBException;

    List<Purchase> getPurchases() throws DBException;
}
