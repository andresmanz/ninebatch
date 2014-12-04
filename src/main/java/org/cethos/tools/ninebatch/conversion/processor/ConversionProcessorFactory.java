package org.cethos.tools.ninebatch.conversion.processor;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.streamprovider.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;

public class ConversionProcessorFactory
{
    public ConversionProcessor createFrom(final String[] args) throws ParseException
    {
        return createBatchConversionProcessorFrom(args);
    }

    private static BatchConversionProcessor createBatchConversionProcessorFrom(final String[] args)
            throws ParseException
    {
        final BatchArgumentParser argumentParser = new BatchArgumentParser();
        final BatchConfig batchConfig = argumentParser.createConfigFrom(args);
        final StreamProvider streamProvider = new RelativeFileStreamProvider(batchConfig);
        return new BatchConversionProcessor(streamProvider);
    }
}
