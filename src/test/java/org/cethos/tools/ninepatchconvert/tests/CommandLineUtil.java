package org.cethos.tools.ninepatchconvert.tests;

public class CommandLineUtil
{
    private CommandLineUtil()
    {
    }

    public static String[] getArgsFrom(final String argumentString)
    {
        return argumentString.split(" ");
    }
}
