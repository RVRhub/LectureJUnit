package com.rybak.model.dao;

import com.rybak.model.dto.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO
{
    public User findById(long id) throws SQLException;
    public boolean create() throws SQLException;
    public void update() throws SQLException;
    public void delete() throws SQLException;
}
