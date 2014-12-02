package org.cethos.tools.ninepatchconvert.conversion;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.cethos.tools.ninepatchconvert.NinePatchOptionUtil;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.PixelRange;

public class SingleArgumentParser
{
    private static final String OPT_INPUT_FILE = "i";
    private static final String OPT_X_SCALING = "xs";
    private static final String OPT_Y_SCALING = "ys";
    private static final String OPT_X_PADDING = "xp";
    private static final String OPT_Y_PADDING = "yp";

    private static final String LONG_OPT_INPUT_FILE = "input";

    private static final String DESC_INPUT_FILE = "input image containing the images and config file";
    private static final String DESC_X_SCALING = "x scaling range, eg '5-10'";
    private static final String DESC_Y_SCALING = "y scaling range, eg '5-10'";
    private static final String DESC_X_PADDING = "x padding range, eg '5-10'";
    private static final String DESC_Y_PADDING = "y padding range, eg '5-10'";

    private final OptionGroup optionGroup;

    public SingleArgumentParser()
    {
        this.optionGroup = createCommandLineOptionGroup();
    }

    private static OptionGroup createCommandLineOptionGroup()
    {
        final Option inputFileOption = createInputFileOption();

        OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(inputFileOption);
        optionGroup.addOption(new Option("s", "process single image"));
        optionGroup.addOption(createPixelRangeConfigOption(OPT_X_SCALING, DESC_X_SCALING));
        optionGroup.addOption(createPixelRangeConfigOption(OPT_Y_SCALING, DESC_Y_SCALING));
        optionGroup.addOption(createPixelRangeConfigOption(OPT_X_PADDING, DESC_X_PADDING));
        optionGroup.addOption(createPixelRangeConfigOption(OPT_Y_PADDING, DESC_Y_PADDING));
        return optionGroup;
    }

    private static Option createInputFileOption()
    {
        final Option option = createFileOption(OPT_INPUT_FILE, LONG_OPT_INPUT_FILE, DESC_INPUT_FILE);
        option.setRequired(true);
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

    public SingleConversionConfig createConfigFrom(final CommandLine commandLine)
    {
        final String inputFilePath = commandLine.getOptionValue(OPT_INPUT_FILE);
        final SingleConversionConfig config = new SingleConversionConfig();
        config.setInputFilePath(inputFilePath);
        config.setNinePatchConfig(createNinePatchConfigFrom(commandLine));
        return config;
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
}
