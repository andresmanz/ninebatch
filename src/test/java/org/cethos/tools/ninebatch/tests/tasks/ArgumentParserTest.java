package org.cethos.tools.ninebatch.tests.tasks;

import org.cethos.tools.ninebatch.tasks.ArgumentParser;
import org.cethos.tools.ninebatch.tasks.ConversionFailureException;
import org.cethos.tools.ninebatch.tasks.RunConfig;
import org.cethos.tools.ninebatch.tests.testutil.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArgumentParserTest
{
    private ArgumentParser argumentHandler;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.argumentHandler = new ArgumentParser();
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
        final RunConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
        assertFalse(config.isDeletingOriginalsEnabled());
        assertFalse(config.isQueryRequested());
    }

    @Test
    public void testCreateConfigFromArguments_inputAndOutputDirectory()
    {
        final String[] args = CommandLineUtil.getArgsFrom("-o testoutputdirectory testinputdirectory");
        final RunConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testoutputdirectory");
        assertFalse(config.isDeletingOriginalsEnabled());
        assertFalse(config.isQueryRequested());
    }

    @Test
    public void testCreateConfigFromArguments_inputDirectoryAndDeleteOriginals()
    {
        final String[] args = CommandLineUtil.getArgsFrom("-d testinputdirectory");
        final RunConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
        assertTrue(config.isDeletingOriginalsEnabled());
        assertFalse(config.isQueryRequested());
    }

    @Test
    public void testCreateConfigFromArguments_inputDirectoryAndQueryRequested()
    {
        final String[] args = CommandLineUtil.getArgsFrom("-q testinputdirectory");
        final RunConfig config = argumentHandler.createConfigFrom(args);
        assertEquals(config.getInputDirPath(), "testinputdirectory");
        assertEquals(config.getOutputDirPath(), "testinputdirectory");
        assertFalse(config.isDeletingOriginalsEnabled());
        assertTrue(config.isQueryRequested());
    }
}
