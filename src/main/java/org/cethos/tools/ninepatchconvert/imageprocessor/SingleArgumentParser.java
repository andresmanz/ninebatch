package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.NinePatchOptionUtil;
import org.cethos.tools.ninepatchconvert.NinePatchUtil;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.PixelRange;

public class SingleArgumentParser
{
    private static final String CMD_LINE_SYNTAX = "ninepatchconvert-convert";

    private static final String OPT_INPUT_FILE = "i";
    private static final String OPT_OUTPUT_FILE = "o";
    private static final String OPT_X_SCALING = "xs";
    private static final String OPT_Y_SCALING = "ys";
    private static final String OPT_X_PADDING = "xp";
    private static final String OPT_Y_PADDING = "yp";

    private static final String LONG_OPT_INPUT_FILE = "input";
    private static final String LONG_OPT_OUTPUT_FILE = "output";

    private static final String DESC_INPUT_FILE = "input image containing the images and config file";
    private static final String DESC_OUTPUT_FILE = "ninepatchconvert output directory";
    private static final String DESC_X_SCALING = "x scaling range, eg '5-10'";
    private static final String DESC_Y_SCALING = "y scaling range, eg '5-10'";
    private static final String DESC_X_PADDING = "x padding range, eg '5-10'";
    private static final String DESC_Y_PADDING = "y padding range, eg '5-10'";

    private final Options options;
    private final CommandLineParser parser;
    private final HelpFormatter helpFormatter;

    public SingleArgumentParser()
    {
        this.options = createCommandLineOptions();
        this.parser = new BasicParser();
        this.helpFormatter = new HelpFormatter();
    }

    private static Options createCommandLineOptions()
    {
        final Option inputFileOption = createInputFileOption();
        final Option outputFileOption = createOutputFileOption();

        Options options = new Options();
        options.addOption(inputFileOption);
        options.addOption(outputFileOption);
        options.addOption(new Option("s", "process single image"));
        options.addOption(createPixelRangeConfigOption(OPT_X_SCALING, DESC_X_SCALING));
        options.addOption(createPixelRangeConfigOption(OPT_Y_SCALING, DESC_Y_SCALING));
        options.addOption(createPixelRangeConfigOption(OPT_X_PADDING, DESC_X_PADDING));
        options.addOption(createPixelRangeConfigOption(OPT_Y_PADDING, DESC_Y_PADDING));
        return  options;
    }

    private static Option createInputFileOption()
    {
        final Option option = createFileOption(OPT_INPUT_FILE, LONG_OPT_INPUT_FILE, DESC_INPUT_FILE);
        option.setRequired(true);
        return option;
    }

    private static Option createOutputFileOption()
    {
        final Option option = createFileOption(OPT_OUTPUT_FILE, LONG_OPT_OUTPUT_FILE, DESC_OUTPUT_FILE);
        option.setRequired(false);
        return option;
    }

    private static Option createFileOption(final String name, final String longName,
                                           final String description)
    {
        final Option option = new Option(name, description);
        option.setArgs(1);
        option.setLongOpt(longName);
        return option;
    }

    private static Option createPixelRangeConfigOption(final String name, final String description)
    {
        return new Option(name, true, description);
    }

    public SingleConversionConfig createConfigFromArguments(final String[] arguments) throws ParseException
    {
        final CommandLine commandLine = parser.parse(options, arguments);
        final String inputFilePath = commandLine.getOptionValue(OPT_INPUT_FILE);
        final String defaultOutputFilePath = getDefaultOutputFilePathFor(inputFilePath);
        final String outputFilePath = commandLine.getOptionValue(OPT_OUTPUT_FILE, defaultOutputFilePath);

        final SingleConversionConfig config = new SingleConversionConfig();
        config.setInputFilePath(inputFilePath);
        config.setOutputFilePath(outputFilePath);
        config.setNinePatchConfig(createNinePatchConfigFrom(commandLine));

        return config;
    }

    private static String getDefaultOutputFilePathFor(final String inputFilePath)
    {
        return NinePatchUtil.getNinePatchFileNameFor(inputFilePath);
    }

    private static NinePatchConfig createNinePatchConfigFrom(final CommandLine commandLine)
    {
        final NinePatchConfig config = new NinePatchConfig();
        setPixelRangeFromOptionIfSet(config.xScalingRange, OPT_X_SCALING, commandLine);
        setPixelRangeFromOptionIfSet(config.yScalingRange, OPT_Y_SCALING, commandLine);
        setPixelRangeFromOptionIfSet(config.xPaddingRange, OPT_X_PADDING, commandLine);
        setPixelRangeFromOptionIfSet(config.yPaddingRange, OPT_Y_PADDING, commandLine);
        return config;
    }

    private static void setPixelRangeFromOptionIfSet(final PixelRange pixelRange,
                                                     final String optionName,
                                                     final CommandLine commandLine)
    {
        if(commandLine.hasOption(optionName))
        {
            final String optionString = commandLine.getOptionValue(optionName);
            pixelRange.set(NinePatchOptionUtil.parsePixelRange(optionString));
        }
    }

    public void printHelp()
    {
        helpFormatter.printHelp(CMD_LINE_SYNTAX, options);
    }
}
