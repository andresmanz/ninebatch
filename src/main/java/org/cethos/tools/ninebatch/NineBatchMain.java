package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.batch.ConversionBatch;
import org.cethos.tools.ninebatch.conversion.ConversionProviderFactory;
import org.cethos.tools.ninebatch.conversion.provider.ConversionProvider;
import org.cethos.tools.ninebatch.creation.FileNinePatchIO;

public class NineBatchMain
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
