package com.playshop.services;

import com.playshop.entity.Person;
import com.playshop.exceptions.DBException;
import com.playshop.exceptions.ServiceException;

public interface UserService extends PersonService {
    void setPurchase(int itemId, int amount, Person person) throws DBException, ServiceException;
}
