package com.rybak.calculator;


import com.rybak.calculator.impl.CalculatorImpl;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CalculatorImplTest
{
    private int num1;
    private int num2;
    private int expectedResult;

    public CalculatorImplTest(int num1, int num2, int expectedResult) {
        this.num1 = num1;
        this.num2 = num2;
        this.expectedResult = expectedResult;
    }

    @Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][] {

                {5, 5, 10},
                {6, 3, 9}
        });
    }

    @Test
    public void addShouldReturnResultAdd()
    {
        int result = calculator.add(num1, num2);
        assertEquals(expectedResult, result);
    }

    @Test
    public void addShouldReturnResultDiv()
    {
        int result = calculator.div(6, 3);
        assertEquals(2, result);
    }

    private Calculator calculator;

    @Before
    public void setup()
    {
        calculator = new CalculatorImpl();
    }


    @Test(expected=IllegalArgumentException.class)
    public void divShouldThrowException()
    {
        calculator.div(50, 0);
    }

    @After
    public void cleanup()
    {
        calculator = null;
    }

}
