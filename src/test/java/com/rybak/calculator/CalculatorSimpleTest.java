package com.rybak.calculator;

import com.rybak.calculator.impl.CalculatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculatorSimpleTest {

    @Test
    public void addShouldReturnRightResult() {
        Calculator calculator=new CalculatorImpl();
        int result=calculator.add(50,5);
        assertEquals(55,result);
    }
}
