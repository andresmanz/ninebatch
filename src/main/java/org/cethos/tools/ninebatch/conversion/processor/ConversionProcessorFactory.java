package org.cethos.tools.ninebatch.conversion.processor;

import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.streamprovider.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;

public class ConversionProcessorFactory
{
    public ConversionProcessor createFrom(final String[] args)
    {
        return createBatchConversionProcessorFrom(args);
    }

    private static BatchConversionProcessor createBatchConversionProcessorFrom(final String[] args)
    {
        final BatchArgumentParser argumentParser = new BatchArgumentParser();
        final BatchConfig batchConfig = argumentParser.createConfigFrom(args);
        final StreamProvider streamProvider = createStreamProviderFrom(batchConfig);
        return new BatchConversionProcessor(streamProvider);
    }

    private static StreamProvider createStreamProviderFrom(final BatchConfig batchConfig)
    {
        final String inputDir = batchConfig.getInputDirPath();
        final String outputDir = batchConfig.getOutputDirPath();
        return new RelativeFileStreamProvider(inputDir, outputDir);
    }
}
