package org.cethos.tools.ninebatch.conversion.batch;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.NinePatchCreation;
import org.cethos.tools.ninebatch.conversion.ConversionIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class ConversionBatch
{
    private final ConversionIO conversionIO;

    public ConversionBatch(final ConversionIO conversionIO)
    {
        this.conversionIO = conversionIO;
    }

    public void process(final Map<String, NinePatchConfig> conversions)
    {
        for(final Map.Entry<String, NinePatchConfig> entry : conversions.entrySet())
        {
            processConversion(entry.getKey(), entry.getValue());
        }
    }

    private void processConversion(final String filePath, final NinePatchConfig ninePatchConfig)
    {
        try
        {
            convertAndSaveNinePatch(filePath, ninePatchConfig);
        }
        catch(final IOException e)
        {
            System.err.println("Could not process image: " + filePath);
            System.err.println(e.getMessage());
        }
    }

    private void convertAndSaveNinePatch(final String inputImageFilePath, final NinePatchConfig config)
            throws IOException
    {
        final BufferedImage inputImage = conversionIO.read(inputImageFilePath);
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        conversionIO.writeNinePatchFor(ninePatch, inputImageFilePath);
    }
}
