package org.cethos.tools.ninepatchconvert;

import org.cethos.tools.ninepatchconvert.imageprocessor.ImageProcessor;
import org.cethos.tools.ninepatchconvert.imageprocessor.ImageProcessorFactory;

public class NinePatchBatchMain
{
    public static void main(final String[] args)
    {
        try
        {
            final ImageProcessorFactory processorFactory = new ImageProcessorFactory();
            final ImageProcessor imageProcessor = processorFactory.createFrom(args);
            imageProcessor.process();
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
}
