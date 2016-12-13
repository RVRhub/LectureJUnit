package com.rybak.service;

import com.rybak.exception.UserSQLException;
import com.rybak.model.dao.UserDAO;
import com.rybak.model.dao.impl.UserDAOImpl;
import com.rybak.model.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.*;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class UserControllerTest
{
    public static final long USER_ID = 5L;
    public static final String FIRST_NAME = "Roman";
    public static final String LAST_NAME = "Rybak";
    private static final int INCORECT_USER_ID = -1;


    @Mock
    private UserDAO userDAO;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }


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


    @Test
    public void findByIdShouldReturnOneUser() throws UserSQLException, SQLException
    {
        //Setup

        UserDAO userDAOInner = mock(UserDAO.class);
        UserController userController = new UserController(userDAOInner);

        User user = new User(USER_ID, FIRST_NAME, LAST_NAME);
        when(userDAOInner.findById(anyLong())).thenReturn(user);

        //Execution
        boolean result = userController.ifUserExists(USER_ID);

        //Verify
        assertTrue(result);
        verify(userDAOInner, times(1)).findById(USER_ID);
    }

    @Test(expected = UserSQLException.class)
    public void checkSqlExceptionInFindById() throws UserSQLException, SQLException
    {
        //Setup

        UserDAO userDAOInner = mock(UserDAO.class);
        UserController userController = new UserController(userDAOInner);

        try {
            when(userDAOInner.findById(anyLong())).thenThrow(new SQLException());

            //Execution
            boolean result = userController.ifUserExists(USER_ID);
        } finally {

            //Verify
            verify(userDAOInner, times(1)).findById(USER_ID);
        }

    }

    @Test
    public void findByIdNotBeCall() throws UserSQLException, SQLException
    {
        //Setup
        UserController userController = new UserController(userDAO);

        //Execution
        boolean result = userController.ifUserExists(INCORECT_USER_ID);

        //Verify
        assertFalse(result);
        verify(userDAO, never()).findById(INCORECT_USER_ID);
    }

    @Test
    public void findByIdShouldCapture() throws UserSQLException, SQLException
    {
        //Setup
        UserController userController = new UserController(userDAO);

        //Execution
        userController.ifUserExists(USER_ID);

        //Verification
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(userDAO).findById(argument.capture());

        assertEquals(USER_ID, (long) argument.getValue());
    }


    @Test
    public void spyTest()
    {
        List<String> list = new LinkedList<>();
        List<String> spyList = spy(list);

        //when(spy.get(0)).thenReturn("foo"); //IndexOutOfBoundsException

        spyList.add(0, "hi");
        assertEquals("hi", spyList.get(0));

        doReturn("foo").when(spyList).get(0);
        spyList.add(0, "hi");

        assertEquals("foo", spyList.get(0));

    }

}
