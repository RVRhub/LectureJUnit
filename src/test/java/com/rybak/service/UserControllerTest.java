package com.rybak.service;

import com.rybak.exception.UserSQLException;
import com.rybak.model.dao.UserDAO;
import com.rybak.model.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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


    @Mock
    private UserDAO userDAO;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdShouldRetrunOneUser() throws UserSQLException, SQLException
    {
        UserController userController = new UserController(userDAO);

        User user = new User(USER_ID, FIRST_NAME, LAST_NAME);
        when(userDAO.findById(anyLong())).thenReturn(user);

        assertTrue(userController.showUsername(USER_ID));
        verify(userDAO, times(1)).findById(USER_ID);
    }


    @Test
    public void spyTest()
    {
        List list = new LinkedList();
        List spy = spy(list);

        spy.add(0, "hi");
        //when(spy.get(0)).thenReturn("foo"); //IndexOutOfBoundsException
        assertEquals("hi", spy.get(0));

        doReturn("foo").when(spy).get(0);
        spy.add(0, "hi");

        assertEquals("foo", spy.get(0));
    }

}
