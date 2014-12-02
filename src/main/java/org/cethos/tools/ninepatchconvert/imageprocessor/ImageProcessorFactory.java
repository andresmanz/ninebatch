package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.batch.BatchArgumentParser;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;

public class ImageProcessorFactory
{
    public ImageProcessor createFrom(final String[] args) throws ParseException
    {
        ImageProcessor imageProcessor;

        if(hasSingleConversionOption(args))
        {
            imageProcessor = createSingleImageProcessor(args);
        }
        else
        {
            imageProcessor = createBatchImageProcessor(args);
        }

        return imageProcessor;
    }

    private static boolean hasSingleConversionOption(final String[] args)
    {
        boolean hasOption = false;
        for(int i = 0; i < args.length; ++i)
        {
            if(args[i].equals("-s"))
            {
                hasOption = true;
            }
        }

        return hasOption;
    }

    private static ImageProcessor createSingleImageProcessor(final String[] args) throws ParseException
    {
        final SingleArgumentParser argumentParser = new SingleArgumentParser();

        try
        {
            final SingleConversionConfig config = argumentParser.createConfigFromArguments(args);
            final SingleImageProcessor imageProcessor = new SingleImageProcessor(config);
            return imageProcessor;
        }
        catch(final ParseException exception)
        {
            argumentParser.printHelp();
            throw exception;
        }
    }

    private static ImageProcessor createBatchImageProcessor(final String[] args) throws ParseException
    {
        final BatchArgumentParser argumentParser = new BatchArgumentParser();

        try
        {
            final BatchConfig batchConfig = argumentParser.createConfigFromArguments(args);
            final BatchImageProcessor imageProcessor = new BatchImageProcessor(batchConfig);
            return imageProcessor;
        }
        catch(final ParseException exception)
        {
            argumentParser.printHelp();
            throw exception;
        }
    }
}
