package org.cethos.tools.ninebatch.tests.conversion;

import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.ConversionFailureException;
import org.cethos.tools.ninebatch.conversion.BatchConfig;
import org.cethos.tools.ninebatch.tests.testutil.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testCreateConfigFromArguments_emptyArguments()
    {
        final String[] arguments = new String[0];
        thrown.expect(ConversionFailureException.class);
        thrown.expectMessage("Missing required option");
        argumentHandler.createConfigFrom(arguments);
    }

    @Test
    public void testCreateConfigFromArguments_inputDirectoryOnly()
    {
        final String[] args = CommandLineUtil.getArgsFrom("testinputdirectory");
        final BatchConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
        assertFalse(config.isDeletingOriginalsEnabled());
    }

    @Test
    public void testCreateConfigFromArguments_inputAndOutputDirectory()
    {
        final String[] args = CommandLineUtil.getArgsFrom("-o testoutputdirectory testinputdirectory");
        final BatchConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testoutputdirectory");
        assertFalse(config.isDeletingOriginalsEnabled());
    }

    @Test
    public void testCreateConfigFromArguments_inputDirectoryAndDeleteOriginals()
    {
        final String[] args = CommandLineUtil.getArgsFrom("-d testinputdirectory");
        final BatchConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
        assertTrue(config.isDeletingOriginalsEnabled());
    }
}
