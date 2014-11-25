package org.cethos.ninepatch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class ConversionBatch
{
    private final BatchConfig batchConfig;

    public ConversionBatch(final BatchConfig batchConfig)
    {
        this.batchConfig = batchConfig;
    }

    public void process(final Map<String, NinePatchConfig> ninePatchConfigs)
    {
        for(final Entry<String, NinePatchConfig> entry : ninePatchConfigs.entrySet())
        {
            try
            {
                final String fileName = entry.getKey();
                final String inputFilePath = batchConfig.getInputPathFor(entry.getKey());
                final BufferedImage inputImage = ImageIO.read(new File(inputFilePath));
                final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, entry.getValue());
                final String ninePatchFileName = getOutFileNameFor(fileName);
                final String outFilePath = batchConfig.getOutputDirPath() + "/" + ninePatchFileName;
                ImageIO.write(ninePatch, "png", new File(outFilePath));
            }
            catch(final IOException e)
            {
                System.err.println("Could not process image: " + e.getMessage());
            }
        }
    }

    private static String getOutFileNameFor(final String inputFileName)
    {
        final StringBuilder stringBuilder = new StringBuilder(inputFileName);
        final int lastPeriodIndex = inputFileName.lastIndexOf(".");
        stringBuilder.insert(lastPeriodIndex, ".9");
        return stringBuilder.toString();
    }
}
