package org.cethos.tools.ninebatch.tests.conversion.argumenthandler;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninebatch.conversion.SingleConversionConfig;
import org.cethos.tools.ninebatch.conversion.argumenthandler.SingleImageArgumentHandler;
import org.cethos.tools.ninebatch.creation.PixelRange;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class SingleImageArgumentHandlerTest
{
    private SingleImageArgumentHandler argumentHandler;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.argumentHandler = new SingleImageArgumentHandler();
    }

    @Test
    public void testCreateConfigFromArguments_withEmptyArguments() throws ParseException
    {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        final CommandLine commandLine = createCommandLine("");
        argumentHandler.createConfigFrom(commandLine);
    }

    @Test
    public void testCreateConfigFromArguments_withInputFile() throws ParseException
    {
        final String argumentString = "-i testinputfile";
        final CommandLine commandLine = createCommandLine(argumentString);
        final SingleConversionConfig config = argumentHandler.createConfigFrom(commandLine);
        assertEquals(config.getInputFilePath(), "testinputfile");
    }

    @Test
    public void testCreateConfigFromArguments_withInputFileAndNinePatchConfig() throws ParseException
    {
        final String argumentString = "-i testinputfile -xs 10-20 -ys 15-25 -xp 5-30 -yp 1-8";
        final CommandLine commandLine = createCommandLine(argumentString);
        final SingleConversionConfig config = argumentHandler.createConfigFrom(commandLine);

        assertEquals(config.getInputFilePath(), "testinputfile");
        assertPixelRangeEquals(10, 20, config.getNinePatchConfig().xScalingRange);
        assertPixelRangeEquals(15, 25, config.getNinePatchConfig().yScalingRange);
        assertPixelRangeEquals(5, 30, config.getNinePatchConfig().xPaddingRange);
        assertPixelRangeEquals(1, 8, config.getNinePatchConfig().yPaddingRange);
    }

    private static void assertPixelRangeEquals(final int begin, final int end, final PixelRange pixelRange)
    {
        assertEquals(begin, pixelRange.getBegin());
        assertEquals(end, pixelRange.getEnd());
    }

    private CommandLine createCommandLine(final String argumentString) throws ParseException
    {
        final CommandLineParser parser = new BasicParser();
        final Options options = argumentHandler.getOptions();
        return parser.parse(options, argumentString.split(" "));
    }
}
