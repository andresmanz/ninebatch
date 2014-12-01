package org.cethos.tools.ninepatchconvert.tests.batch;

import org.cethos.tools.ninepatchconvert.creation.ImageInputOutput;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;

public class MockImageInputOutput implements ImageInputOutput
{
    private int totalWriteCount;
    private int totalReadCount;

    @Override
    public void write(final RenderedImage image, final Path filePath) throws IOException
    {
        ++totalWriteCount;
    }

    @Override
    public BufferedImage read(final Path filePath) throws IOException
    {
        ++totalReadCount;
        return new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
    }

    public int getTotalWriteCount()
    {
        return totalWriteCount;
    }

    public int getTotalReadCount()
    {
        return totalReadCount;
    }
}
