package org.cethos.tools.ninebatch.tests.conversion;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.tests.testutil.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BatchArgumentParserTest
{
    private BatchArgumentParser argumentHandler;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.argumentHandler = new BatchArgumentParser();
    }

    @Test
    public void testCreateConfigFromArguments_withEmptyArguments() throws ParseException
    {
        final String[] arguments = new String[0];
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        argumentHandler.createConfigFrom(arguments);
    }

    @Test
    public void testCreateConfigFromArguments_withInputDirectoryOnly() throws ParseException
    {
        final String[] args = CommandLineUtil.getArgsFrom("testinputdirectory");
        final BatchConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
    }

    @Test
    public void testCreateConfigFromArguments_withInputAndOutputDirectory() throws ParseException
    {
        final String[] args = CommandLineUtil.getArgsFrom("-o testoutputdirectory testinputdirectory");
        final BatchConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testoutputdirectory");
    }
}
