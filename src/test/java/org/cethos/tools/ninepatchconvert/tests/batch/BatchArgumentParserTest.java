package org.cethos.tools.ninepatchconvert.tests.batch;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.batch.BatchArgumentParser;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

public class BatchArgumentParserTest
{
    private BatchArgumentParser argumentParser;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.argumentParser = new BatchArgumentParser();
    }

    @Test
    public void testCreateConfigFromArguments_withEmptyArguments() throws ParseException
    {
        final String[] arguments = new String[0];
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        argumentParser.createConfigFromArguments(arguments);
    }

    @Test
    public void testCreateConfigFromArguments_withInputAndOutputDirectory() throws ParseException
    {
        final String[] arguments = new String[4];
        arguments[0] = "-i";
        arguments[1] = "testinputdirectory";
        arguments[2] = "-o";
        arguments[3] = "testoutputdirectory";
        
        final BatchConfig config = argumentParser.createConfigFromArguments(arguments);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testoutputdirectory");
    }
}
