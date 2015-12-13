package com.rybak.service;


import com.rybak.exception.UserSQLException;
import com.rybak.model.dao.UserDAO;
import com.rybak.model.dto.User;

import java.sql.SQLException;

public class UserController
{
    public static final String CAN_NOT_EXECUTE_SQL_ERROR_MESSAGE = "Сan not execute the SQL request.";
    public static final String ERROR_CODE = "1001";

    private UserDAO userDAO;

    public UserController(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public boolean showUsername(long id) throws UserSQLException {

        try
        {
            User currentUser = userDAO.findById(id);

            if(currentUser == null)
            {
                return false;
            }
            return true;
        }
        catch (SQLException e)
        {
            //log.error(e, e);
            throw new UserSQLException(CAN_NOT_EXECUTE_SQL_ERROR_MESSAGE, ERROR_CODE);
        }

    }

}
