package org.cethos.tools.ninebatch.tasks;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser
{
    private static final String CMD_LINE_SYNTAX = "ninebatch [OPTIONS] input-directory";
    private static final String OPT_OUTPUT_DIR = "o";
    private static final String OPT_DELETE_ORIGINALS = "d";
    private static final String OPT_QUERY_IMAGES = "q";

    private static final String LONG_OPT_OUTPUT_DIR = "output-directory";
    private static final String LONG_OPT_DELETE_ORIGINALS = "delete-originals";
    private static final String LONG_OPT_QUERY_IMAGES = "query-images";

    private static final String DESC_OUTPUT_DIR = "ninebatch output directory";
    private static final String DESC_DELETE_ORIGINALS = "delete original images";
    private static final String DESC_QUERY_IMAGES = "query input image list; won't convert anything";

    private final Options options;
    private final HelpFormatter helpFormatter;
    private final CommandLineParser commandLineParser;

    public ArgumentParser()
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
        options.addOption(createQueryImagesOption());
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

    private static Option createQueryImagesOption()
    {
        final Option option = new Option(OPT_QUERY_IMAGES, false, DESC_QUERY_IMAGES);
        option.setLongOpt(LONG_OPT_QUERY_IMAGES);
        return option;
    }

    public RunConfig createConfigFrom(final String[] args)
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

    private RunConfig tryCreatingBatchConfigFrom(final String[] args) throws ParseException
    {
        final CommandLine commandLine = commandLineParser.parse(options, args);

        final String inputDirPath = getInputDirPathFrom(commandLine);
        final String outputDirPath = commandLine.getOptionValue(OPT_OUTPUT_DIR, inputDirPath);
        final boolean isDeletingOriginalsEnabled = commandLine.hasOption(OPT_DELETE_ORIGINALS);
        final boolean isQueryRequested = commandLine.hasOption(OPT_QUERY_IMAGES);

        final RunConfig runConfig = new RunConfig(inputDirPath, outputDirPath);
        runConfig.setDeletingOriginalsEnabled(isDeletingOriginalsEnabled);
        runConfig.setQueryRequested(isQueryRequested);
        return runConfig;
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
