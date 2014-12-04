package org.cethos.tools.ninebatch.tests.testutil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertTrue;

public class Assert
{
    private Assert()
    {
    }

    public static <T> void assertConstructorIsPrivate(final Class<T> classToTest) throws Exception
    {
        Constructor<T> constructor = classToTest.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
