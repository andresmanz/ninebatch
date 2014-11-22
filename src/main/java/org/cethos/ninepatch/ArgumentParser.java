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
        final String description = "input directory containing the images and config file";
        final Option inputDirOption = new Option("i", "input-directory", true, description);
        inputDirOption.setRequired(true);
        return inputDirOption;
    }

    private static Option createOutputDirectoryOption()
    {
        final String description = "ninepatch output directory";
        final Option outputDirOption = new Option("o", "output-directory", true, description);
        outputDirOption.setRequired(true);
        return outputDirOption;
    }

    public BatchConfig createConfigFromArguments(final String[] arguments) throws ParseException
    {
        final CommandLine commandLine = parser.parse(options, arguments);
        final String inputDirPath = commandLine.getOptionValue("i");
        final String outputDirPath = commandLine.getOptionValue("o");

        final BatchConfig batchConfig = new BatchConfig();
        batchConfig.setInputDirPath(inputDirPath);
        batchConfig.setOutputDirPath(outputDirPath);
        return batchConfig;
    }

    public void printHelp()
    {
        helpFormatter.printHelp("ninepatch-convert", options);
    }
}
