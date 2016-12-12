package com.rybak.service;

import com.rybak.exception.UserSQLException;
import com.rybak.model.dao.UserDAO;
import com.rybak.model.dao.impl.UserDAOImpl;
import com.rybak.model.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * Created by roman on 12.12.16.
 */
public class UserControllerSimpleTest {

    public static final long USER_ID = 5L;
    public static final String FIRST_NAME = "Roman";
    public static final String LAST_NAME = "Rybak";

    @Test
    public void findByIdWithoutMockito() throws UserSQLException, SQLException
    {
        //Setup
        UserDAO userDAOMock = new UserDAOImpl()
        {
            public User findById(long id) throws SQLException {
                return new User(USER_ID, FIRST_NAME, LAST_NAME);
            }
        };
        UserController userController = new UserController(userDAOMock);

        //Execution
        boolean result = userController.ifUserExists(USER_ID);

        //Verify
        assertTrue(result);
    }
}
