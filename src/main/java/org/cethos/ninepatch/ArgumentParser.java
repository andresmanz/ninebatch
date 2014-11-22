package org.cethos.ninepatch;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentParser
{
    private static final String CMD_LINE_SYNTAX = "ninepatch-convert";
    private static final String OPT_INPUT_DIR = "i";
    private static final String OPT_OUTPUT_DIR = "o";
    private static final String LONG_OPT_INPUT_DIR = "input-directory";
    private static final String LONG_OPT_OUTPUT_DIR = "output-directory";
    private static final String DESC_INPUT_DIR = "input directory containing the images and config file";
    private static final String DESC_OUTPUT_DIR = "ninepatch output directory";

    private final Options options;
    private final CommandLineParser parser;
    private final HelpFormatter helpFormatter;

    public ArgumentParser()
    {
        this.options = createCommandLineOptions();
        this.parser = new BasicParser();
        this.helpFormatter = new HelpFormatter();
    }

    private static Options createCommandLineOptions()
    {
        final Option inputDirOption = createInputDirectoryOption();
        final Option outputDirOption = createOutputDirectoryOption();

        Options options = new Options();
        options.addOption(inputDirOption);
        options.addOption(outputDirOption);
        return  options;
    }

    private static Option createInputDirectoryOption()
    {
        final Option option = createDirectoryOption(OPT_INPUT_DIR, LONG_OPT_INPUT_DIR, DESC_INPUT_DIR);
        option.setRequired(true);
        return option;
    }

    private static Option createOutputDirectoryOption()
    {
        final Option option = createDirectoryOption(OPT_OUTPUT_DIR, LONG_OPT_OUTPUT_DIR, DESC_OUTPUT_DIR);
        option.setRequired(false);
        return option;
    }

    private static Option createDirectoryOption(final String name, final String longName,
                                                final String description)
    {
        final Option option = new Option(name, description);
        option.setArgs(1);
        option.setLongOpt(longName);
        return option;
    }

    public BatchConfig createConfigFromArguments(final String[] arguments) throws ParseException
    {
        final CommandLine commandLine = parser.parse(options, arguments);
        final String inputDirPath = commandLine.getOptionValue(OPT_INPUT_DIR);
        final String outputDirPath = commandLine.getOptionValue(OPT_OUTPUT_DIR, inputDirPath);
        final BatchConfig batchConfig = new BatchConfig(inputDirPath, outputDirPath);
        return batchConfig;
    }

    public void printHelp()
    {
        helpFormatter.printHelp(CMD_LINE_SYNTAX, options);
    }
}
