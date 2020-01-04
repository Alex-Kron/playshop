package com.playshop.services;

import com.playshop.entity.Item;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import java.util.List;

public interface AdminService extends PersonService {

    int createItem(Item item) throws DBException;

    int getItemId(Item item) throws DBException;

    void updateItem(int id, Item item) throws DBException;

    void deleteItem(int id) throws DBException;

    void deleteAllItems() throws DBException;

    void deleteAllPersons() throws DBException;

    void setAdmin(int id) throws DBException;

    void deleteAllPurchases() throws DBException;

    List<Purchase> getPurchases() throws DBException;
}
