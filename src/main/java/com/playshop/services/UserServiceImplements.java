package com.playshop.services;

import com.playshop.dao.ItemDAO;
import com.playshop.dao.PersonDAO;
import com.playshop.dao.PurchaseDAO;
import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.entity.Purchase;
import com.playshop.exceptions.DBException;
import com.playshop.exceptions.ServiceException;

import java.sql.SQLException;
import java.util.logging.Logger;

public class UserServiceImplements extends PersonServiceImplements implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImplements.class.getName());

    @Override
    public void setPurchase(int itemId, int amount, Person person) throws DBException, ServiceException {
        setPurchase(itemId, amount, person, false);
    }

    public void setPurchase(int itemId, int amount, Person person, boolean test) throws DBException, ServiceException {
        try {
            ItemDAO itemDAO = new ItemDAO(test);
            Item item = itemDAO.get(itemId);

            if (!checkConditionPurchase(item, amount)) {
                throw new ServiceException("No item for purchase");
            }

            int personId = new PersonDAO(test).getId(person);

            Purchase purchase = new Purchase(personId, itemId, amount);
            PurchaseDAO purchaseDAO = new PurchaseDAO(test);
            int purchaseId = purchaseDAO.create(purchase);
            purchase = purchaseDAO.get(purchaseId);
            item.setQuantity(item.getQuantity() - amount);
            itemDAO.update(itemId, item);
            logger.fine("Item purchased: " + purchase);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private boolean checkConditionPurchase(Item item, int amount) {
        return item != null && amount <= item.getQuantity();
    }
}
