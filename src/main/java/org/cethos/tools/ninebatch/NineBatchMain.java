package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.streamprovider.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;

public class NineBatchMain
{
    public static void main(final String[] args)
    {
        try
        {
            final BatchArgumentParser argumentParser = new BatchArgumentParser();
            final BatchConfig batchConfig = argumentParser.createConfigFrom(args);
            final StreamProvider streamProvider = new RelativeFileStreamProvider(batchConfig);
            final DefaultConversionProcessor processor = new DefaultConversionProcessor(streamProvider);
            processor.loadAndProcessConversions();
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
}
