package org.cethos.tools.ninepatchconvert.batch;

import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchCreation;
import org.cethos.tools.ninepatchconvert.creation.NinePatchIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class ConversionBatch
{
    private final NinePatchIO ninePatchIO;

    public ConversionBatch(final NinePatchIO ninePatchIO)
    {
        this.ninePatchIO = ninePatchIO;
    }

    public void process(final Map<String, NinePatchConfig> ninePatchConfigs)
    {
        for(final Entry<String, NinePatchConfig> entry : ninePatchConfigs.entrySet())
        {
            processNinePatchConfigEntry(entry);
        }
    }

    private void processNinePatchConfigEntry(final Entry<String, NinePatchConfig> entry)
    {
        final String fileName = entry.getKey();
        final NinePatchConfig currentConfig = entry.getValue();

        try
        {
            convertAndSaveNinePatch(fileName, currentConfig);
        }
        catch(final IOException e)
        {
            System.err.println("Could not process image: " + fileName);
            System.err.println(e.getMessage());
        }
    }

    private void convertAndSaveNinePatch(final String imageFileName, final NinePatchConfig config)
            throws IOException
    {
        final BufferedImage inputImage = ninePatchIO.read(imageFileName);
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        ninePatchIO.writeNinePatchFor(ninePatch, imageFileName);
    }
}
