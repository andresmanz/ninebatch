package org.cethos.tools.ninebatch;

import org.cethos.tools.ninebatch.tasks.ConversionFailureException;
import org.cethos.tools.ninebatch.tasks.Task;
import org.cethos.tools.ninebatch.tasks.TaskFactory;
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
            final TaskFactory factory = new TaskFactory();
            final Task processor = factory.createFrom(args);
            processor.process();
        }
        catch(final ConversionFailureException exception)
        {
            LOGGER.error("Error processing conversions", exception);
        }
    }
}
