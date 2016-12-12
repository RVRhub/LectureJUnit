package com.rybak.model.dao.impl;

import com.rybak.model.dao.UserDAO;
import com.rybak.model.dto.User;
import com.rybak.util.IDGenerator;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO
{


    public User findById(long id) throws SQLException {
        //Call to DB
        return null;
    }

    public long create(User user) throws SQLException
    {
        long id = IDGenerator.generateId();
        //save the user object to the db

        return id;
    }

    public void update() throws SQLException {

    }

    public void delete() throws SQLException {

    }
}
