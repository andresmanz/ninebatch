package org.cethos.tools.ninebatch.conversion;

import org.cethos.tools.ninebatch.conversion.io.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.conversion.io.StreamProvider;

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

        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        processor.setDeletingImageSourcesEnabled(batchConfig.isDeletingOriginalsEnabled());
        return processor;
    }

    private static StreamProvider createStreamProviderFrom(final BatchConfig batchConfig)
    {
        final String inputDir = batchConfig.getInputDirPath();
        final String outputDir = batchConfig.getOutputDirPath();
        return new RelativeFileStreamProvider(inputDir, outputDir);
    }
}
