package com.rybak.util;


public final class IDGenerator
{
    private static long id;

    public static final long generateId()
    {
        return id++;
    }
}
