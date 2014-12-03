package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.conversion.BatchArgumentParser;
import org.cethos.tools.ninebatch.conversion.ConversionParsing;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.batch.ConversionBatch;
import org.cethos.tools.ninebatch.conversion.StreamConversionIO;
import org.cethos.tools.ninebatch.conversion.streamprovider.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.util.Map;

public class NineBatchMain
{

    public static void main(final String[] args)
    {
        try
        {
            final BatchArgumentParser argumentParser = new BatchArgumentParser();
            final BatchConfig batchConfig = argumentParser.createConfigFrom(args);
            final StreamProvider streamProvider = new RelativeFileStreamProvider(batchConfig);

            final StreamConversionIO conversionIO = new StreamConversionIO(streamProvider);
            final String conversionJson = conversionIO.loadConversionJson();
            final Map<String, NinePatchConfig> conversions = ConversionParsing.parse(conversionJson);
            final ConversionBatch conversionBatch = new ConversionBatch(streamProvider);
            conversionBatch.process(conversions);
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
}
