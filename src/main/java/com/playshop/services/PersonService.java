package com.playshop.services;

import com.playshop.entity.Item;
import com.playshop.entity.Person;
import com.playshop.exceptions.DBException;
import java.util.List;


public interface PersonService {

    Person signIn(String name, String password) throws DBException;

    Person signUp(String name, String password) throws DBException;

    Person signUp(String name, String password, boolean admin) throws DBException;

    Item getItem(long id) throws DBException;

    List<Item> getItems(Person person) throws DBException;
}
