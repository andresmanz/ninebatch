package org.cethos.tools.ninebatch.conversion.batch;

import org.cethos.tools.ninebatch.conversion.io.StreamConversionIO;
import org.cethos.tools.ninebatch.conversion.io.StreamProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.NinePatchCreation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

public class ConversionBatch
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionBatch.class);

    private final StreamConversionIO streamConversionIO;

    public ConversionBatch(final StreamProvider streamProvider)
    {
        this.streamConversionIO = new StreamConversionIO(streamProvider);
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
        catch(final IOException exception)
        {
            LOGGER.warn("Could not process image", exception);
        }
    }

    private void convertAndSaveNinePatch(final String inputImageFilePath, final NinePatchConfig config)
            throws IOException
    {
        final BufferedImage inputImage = streamConversionIO.read(inputImageFilePath);
        final RenderedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        streamConversionIO.writeNinePatchFor(ninePatch, inputImageFilePath);
    }
}
