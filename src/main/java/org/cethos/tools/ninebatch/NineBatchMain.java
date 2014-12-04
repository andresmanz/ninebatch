package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessor;
import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessorFactory;

public class NineBatchMain
{
    public static void main(final String[] args)
    {
        try
        {
            final ConversionProcessorFactory factory = new ConversionProcessorFactory();
            final ConversionProcessor processor = factory.createFrom(args);
            processor.loadAndProcessConversions();
        }
        catch(final Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
