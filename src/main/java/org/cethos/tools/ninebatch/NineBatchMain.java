package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.conversion.ConversionFailureException;
import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessor;
import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NineBatchMain
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NineBatchMain.class);

    private NineBatchMain()
    {
    }

    public static void main(final String[] args)
    {
        try
        {
            final ConversionProcessorFactory factory = new ConversionProcessorFactory();
            final ConversionProcessor processor = factory.createFrom(args);
            processor.loadAndProcessConversions();
        }
        catch(final ConversionFailureException exception)
        {
            LOGGER.error("Error processing conversions", exception);
        }
    }
}
