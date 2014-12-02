package org.cethos.tools.ninepatchconvert;

import org.cethos.tools.ninepatchconvert.imageprocessor.BatchImageProcessor;
import org.cethos.tools.ninepatchconvert.imageprocessor.ImageProcessor;

public class NinePatchBatchMain
{
    public static void main(final String[] args)
    {
        try
        {
            final ImageProcessor imageProcessor = new BatchImageProcessor(args);
            imageProcessor.process();
        }
        catch(final Exception e)
        {
            e.printStackTrace();
        }
    }
}
