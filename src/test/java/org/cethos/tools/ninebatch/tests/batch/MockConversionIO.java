package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.conversion.ConversionIO;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

public class MockConversionIO implements ConversionIO
{
    private int totalWriteCount;
    private int totalReadCount;

    public int getTotalWriteCount()
    {
        return totalWriteCount;
    }

    public int getTotalReadCount()
    {
        return totalReadCount;
    }

    @Override
    public void writeNinePatchFor(final RenderedImage image, final String fileName) throws IOException
    {
        ++totalWriteCount;
    }

    @Override
    public BufferedImage read(final String fileName) throws IOException
    {
        ++totalReadCount;
        return new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public String loadConversionJson() throws IOException
    {
        return null;
    }
}
