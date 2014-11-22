package org.cethos.ninepatch;

import org.apache.commons.cli.ParseException;

public class NinePatchConvert
{
    public static void main(final String[] args)
    {
        final ArgumentParser argumentParser = new ArgumentParser();

        try
        {
            final BatchConfig config = argumentParser.createConfigFromArguments(args);
        }
        catch(final ParseException e)
        {
            System.err.println(e.getMessage());
            argumentParser.printHelp();
        }
    }
}
