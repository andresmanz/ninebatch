package org.cethos.tools.ninepatchconvert.conversion;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;

public class BatchArgumentHandler
{
    private static final String OPT_INPUT_DIR = "i";
    private static final String OPT_OUTPUT_DIR = "o";
    private static final String LONG_OPT_INPUT_DIR = "input-directory";
    private static final String LONG_OPT_OUTPUT_DIR = "output-directory";
    private static final String DESC_INPUT_DIR = "input directory containing the images and config file";
    private static final String DESC_OUTPUT_DIR = "ninepatchconvert output directory";

    private final Options options;

    public BatchArgumentHandler()
    {
        this.options = createCommandLineOptions();
    }

    public Options getOptions()
    {
        return options;
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

    public BatchConfig createConfigFrom(final CommandLine commandLine)
    {
        final String inputDirPath = commandLine.getOptionValue(OPT_INPUT_DIR);
        final String outputDirPath = commandLine.getOptionValue(OPT_OUTPUT_DIR, inputDirPath);
        final BatchConfig batchConfig = new BatchConfig(inputDirPath, outputDirPath);
        return batchConfig;
    }
}
