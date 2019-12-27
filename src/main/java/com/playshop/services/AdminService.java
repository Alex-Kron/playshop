package com.playshop.services;

import com.playshop.entity.Item;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import java.util.List;

public interface AdminService extends PersonService {

    long createItem(Item item) throws DBException;

    void updateItem(Item item) throws DBException;

    void deleteItem(long id) throws DBException;

    void deleteAllItems() throws DBException;

    void deleteAllPersons() throws DBException;

    void deleteAllPurchases() throws DBException;

    List<Purchase> getPurchases() throws DBException;
}
