package org.cethos.tools.ninebatch.conversion;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BatchArgumentParser
{
    private static final String CMD_LINE_SYNTAX = "ninebatch [OPTIONS] input-directory";
    private static final String OPT_OUTPUT_DIR = "o";
    private static final String OPT_DELETE_ORIGINALS = "d";
    private static final String LONG_OPT_OUTPUT_DIR = "output-directory";
    private static final String LONG_OPT_DELETE_ORIGINALS = "delete-originals";
    private static final String DESC_OUTPUT_DIR = "ninebatch output directory";
    private static final String DESC_DELETE_ORIGINALS = "delete original images";

    private final Options options;
    private final HelpFormatter helpFormatter;
    private final CommandLineParser commandLineParser;

    public BatchArgumentParser()
    {
        this.options = createCommandLineOptions();
        this.helpFormatter = new HelpFormatter();
        this.commandLineParser = new BasicParser();
    }

    private static Options createCommandLineOptions()
    {
        final Options options = new Options();
        options.addOption(createOutputDirectoryOption());
        options.addOption(createDeleteOriginalsOption());
        return options;
    }

    private static Option createOutputDirectoryOption()
    {
        final Option option = new Option(OPT_OUTPUT_DIR, DESC_OUTPUT_DIR);
        option.setArgs(1);
        option.setLongOpt(LONG_OPT_OUTPUT_DIR);
        return option;
    }

    private static Option createDeleteOriginalsOption()
    {
        final Option option = new Option(OPT_DELETE_ORIGINALS, false, DESC_DELETE_ORIGINALS);
        option.setLongOpt(LONG_OPT_DELETE_ORIGINALS);
        return option;
    }

    public BatchConfig createConfigFrom(final String[] args)
    {
        try
        {
            return tryCreatingBatchConfigFrom(args);
        }
        catch(final ParseException exception)
        {
            printHelp();
            throw new ConversionFailureException(exception);
        }
    }

    private BatchConfig tryCreatingBatchConfigFrom(final String[] args) throws ParseException
    {
        final CommandLine commandLine = commandLineParser.parse(options, args);

        final String inputDirPath = getInputDirPathFrom(commandLine);
        final String outputDirPath = commandLine.getOptionValue(OPT_OUTPUT_DIR, inputDirPath);
        final boolean isDeletingOriginalsEnabled = commandLine.hasOption(OPT_DELETE_ORIGINALS);

        final BatchConfig batchConfig = new BatchConfig(inputDirPath, outputDirPath);
        batchConfig.setDeletingOriginalsEnabled(isDeletingOriginalsEnabled);
        return batchConfig;
    }

    private String getInputDirPathFrom(final CommandLine commandLine) throws ParseException
    {
        if(!commandLine.getArgList().isEmpty())
        {
            return (String)commandLine.getArgList().get(0);
        }
        else
        {
            throw new ParseException("Missing required option: input directory");
        }
    }

    public void printHelp()
    {
        helpFormatter.printHelp(CMD_LINE_SYNTAX, options);
    }
}
