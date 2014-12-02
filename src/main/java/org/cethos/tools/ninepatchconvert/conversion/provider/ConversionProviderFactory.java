package org.cethos.tools.ninepatchconvert.conversion.provider;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.cethos.tools.ninepatchconvert.conversion.BatchArgumentHandler;
import org.cethos.tools.ninepatchconvert.conversion.SingleArgumentParser;
import org.cethos.tools.ninepatchconvert.conversion.SingleConversionConfig;

import java.io.IOException;

public class ConversionProviderFactory
{
    private static final String OPT_SINGLE_IMAGE = "s";
    private static final String DESC_SINGLE_IMAGE = "process single image";

    public ConversionProvider createFrom(final String[] args) throws ParseException, IOException
    {
        final CommandLineParser commandLineParser = new BasicParser();
        final BatchArgumentHandler batchArgumentHandler = new BatchArgumentHandler();

        final Options options = new Options();
        options.addOption(OPT_SINGLE_IMAGE, false, DESC_SINGLE_IMAGE);

        final CommandLine commandLine = commandLineParser.parse(options, args);

        ConversionProvider provider;

        if(commandLine.hasOption(OPT_SINGLE_IMAGE))
        {
            provider = createSingleConversionProvider(commandLine);
        }
        else
        {
            provider = createBatchConfigConversionProvider(commandLine);
        }

        return provider;
    }

    private ConversionProvider createSingleConversionProvider(final CommandLine commandLine)
    {
        final SingleArgumentParser argumentParser = new SingleArgumentParser();
        final SingleConversionConfig config = argumentParser.createConfigFrom(commandLine);
        return new SingleConversionProvider(config);
    }

    private ConversionProvider createBatchConfigConversionProvider(final CommandLine commandLine)
    {
        final BatchArgumentHandler argumentParser = new BatchArgumentHandler();
        final BatchConfig batchConfig = argumentParser.createConfigFrom(commandLine);
        return new BatchConfigConversionProvider(batchConfig);
    }
}
