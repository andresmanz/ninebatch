package org.cethos.tools.ninebatch.tests.testutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class Assert
{
    private Assert()
    {
    }

    public static <T> void assertConstructorIsPrivate(final Class<T> classToTest)
            throws IllegalAccessException, InvocationTargetException,
                   InstantiationException, NoSuchMethodException
    {
        Constructor<T> constructor = classToTest.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
