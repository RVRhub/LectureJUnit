package com.rybak.model.dao;

import com.rybak.model.dao.impl.UserDAOImpl;
import com.rybak.model.dto.User;
import com.rybak.util.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.SQLException;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.junit.Assert.*;


@RunWith(PowerMockRunner.class)
@PrepareForTest(IDGenerator.class)
public class UserDAOTest
{

    @Test
    public void createShouldReturnUserId() throws SQLException {
        UserDAO userDAO = new UserDAOImpl();

        mockStatic(IDGenerator.class);
        when(IDGenerator.generateId()).thenReturn(1L);

        long result = userDAO.create(new User());

        assertEquals(1, result);
        verifyStatic();
    }

}
