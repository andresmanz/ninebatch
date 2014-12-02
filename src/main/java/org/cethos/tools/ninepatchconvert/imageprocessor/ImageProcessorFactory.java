package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.batch.BatchArgumentParser;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;

public class ImageProcessorFactory
{
    public ImageProcessor createFrom(final String[] args) throws ParseException
    {
        return createBatchImageProcessor(args);
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
