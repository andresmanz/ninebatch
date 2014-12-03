package org.cethos.tools.ninepatchconvert.tests.conversion.argumenthandler;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.conversion.argumenthandler.BatchArgumentHandler;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BatchArgumentHandlerTest
{
    private BatchArgumentHandler argumentHandler;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.argumentHandler = new BatchArgumentHandler();
    }

    @Test
    public void testCreateConfigFromArguments_withEmptyArguments() throws ParseException
    {
        final String[] arguments = new String[0];
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        final CommandLine commandLine = createCommandLine(arguments);
        argumentHandler.createConfigFrom(commandLine);
    }

    @Test
    public void testCreateConfigFromArguments_withInputAndOutputDirectory() throws ParseException
    {
        final String[] arguments = new String[4];
        arguments[0] = "-i";
        arguments[1] = "testinputdirectory";
        arguments[2] = "-o";
        arguments[3] = "testoutputdirectory";

        final CommandLine commandLine = createCommandLine(arguments);
        final BatchConfig config = argumentHandler.createConfigFrom(commandLine);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testoutputdirectory");
    }

    private CommandLine createCommandLine(final String[] args) throws ParseException
    {
        final CommandLineParser parser = new BasicParser();
        final Options options = argumentHandler.getOptions();
        return parser.parse(options, args);
    }
}
