package org.cethos.tools.ninepatchconvert;

import org.cethos.tools.ninepatchconvert.batch.ConversionBatch;
import org.cethos.tools.ninepatchconvert.conversion.provider.ConversionProvider;
import org.cethos.tools.ninepatchconvert.creation.FileNinePatchIO;
import org.cethos.tools.ninepatchconvert.conversion.ConversionProviderFactory;

public class NinePatchBatchMain
{
    public static void main(final String[] args)
    {
        try
        {
            final ConversionProviderFactory providerFactory = new ConversionProviderFactory();
            final ConversionProvider provider = providerFactory.createFrom(args);
            final ConversionBatch conversionBatch = new ConversionBatch();
            conversionBatch.add(provider.createConfigs());
            conversionBatch.process(new FileNinePatchIO());
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
}
