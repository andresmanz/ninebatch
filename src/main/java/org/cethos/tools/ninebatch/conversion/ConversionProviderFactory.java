package org.cethos.tools.ninebatch.conversion;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninebatch.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.argumenthandler.BatchArgumentHandler;
import org.cethos.tools.ninebatch.conversion.argumenthandler.SingleImageArgumentHandler;
import org.cethos.tools.ninebatch.conversion.provider.BatchConfigConversionProvider;
import org.cethos.tools.ninebatch.conversion.provider.ConversionProvider;
import org.cethos.tools.ninebatch.conversion.provider.SingleConversionProvider;

import java.io.IOException;

public class ConversionProviderFactory
{
    private static final String OPT_SINGLE_IMAGE = "-s";
    private static final String CMD_LINE_SYNTAX = "ninebatch [-s] [OPTIONS]";

    private final HelpFormatter helpFormatter;
    private final CommandLineParser commandLineParser;

    public ConversionProviderFactory()
    {
        this.helpFormatter = new HelpFormatter();
        this.commandLineParser = new BasicParser();
    }

    public ConversionProvider createFrom(final String[] args) throws ParseException, IOException
    {
        ConversionProvider provider;

        if(isSetToSingleImage(args))
        {
            provider = createSingleConversionProviderFrom(args);
        }
        else
        {
            provider = createBatchConfigConversionProviderFrom(args);
        }

        return provider;
    }

    private boolean isSetToSingleImage(final String[] args) throws ParseException
    {
        return ((args.length > 0) && (args[0].equals(OPT_SINGLE_IMAGE)));
    }

    private ConversionProvider createSingleConversionProviderFrom(final String[] args) throws ParseException
    {
        final SingleImageArgumentHandler argumentParser = new SingleImageArgumentHandler();
        final Options options = argumentParser.getOptions();
        final CommandLine commandLine = parseAndGetCommandLine(options, args);
        final SingleConversionConfig config = argumentParser.createConfigFrom(commandLine);
        return new SingleConversionProvider(config);
    }

    private ConversionProvider createBatchConfigConversionProviderFrom(final String[] args) throws ParseException
    {
        final BatchArgumentHandler argumentParser = new BatchArgumentHandler();
        final Options options = argumentParser.getOptions();
        final CommandLine commandLine = parseAndGetCommandLine(options, args);
        final BatchConfig batchConfig = argumentParser.createConfigFrom(commandLine);
        return new BatchConfigConversionProvider(batchConfig);
    }

    private CommandLine parseAndGetCommandLine(final Options options, final String[] args) throws ParseException
    {
        try
        {
            final CommandLine commandLine = commandLineParser.parse(options, args);
            return commandLine;
        }
        catch(final ParseException e)
        {
            helpFormatter.printHelp(CMD_LINE_SYNTAX, options);
            throw e;
        }
    }
}
