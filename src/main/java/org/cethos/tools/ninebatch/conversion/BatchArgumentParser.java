package org.cethos.tools.ninebatch.conversion;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;

public class BatchArgumentParser
{
    private static final String OPT_INPUT_DIR = "i";
    private static final String OPT_OUTPUT_DIR = "o";
    private static final String LONG_OPT_INPUT_DIR = "input-directory";
    private static final String LONG_OPT_OUTPUT_DIR = "output-directory";
    private static final String DESC_INPUT_DIR = "input directory containing the images and config file";
    private static final String DESC_OUTPUT_DIR = "ninebatch output directory";
    private static final String CMD_LINE_SYNTAX = "ninebatch [OPTIONS] input-directory";

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
        final Option inputDirOption = createInputDirectoryOption();
        final Option outputDirOption = createOutputDirectoryOption();

        final Options optionGroup = new Options();
        optionGroup.addOption(inputDirOption);
        optionGroup.addOption(outputDirOption);
        return optionGroup;
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

    public BatchConfig createConfigFrom(final String[] args) throws ParseException
    {
        final CommandLine commandLine = commandLineParser.parse(options, args);
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
