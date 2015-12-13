package com.rybak.calculator.impl;

import com.rybak.calculator.Calculator;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CalculatorImpl implements Calculator
{
    public int add(int num1, int num2)
    {
        return num1 + num2;
    }

    public int div(int num1, int num2)
    {
        if(num2 == 0)
            throw new IllegalArgumentException();

        return num1/num2;
    }


}
