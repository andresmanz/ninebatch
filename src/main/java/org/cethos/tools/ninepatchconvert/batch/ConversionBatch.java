package org.cethos.tools.ninepatchconvert.batch;

import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchCreation;
import org.cethos.tools.ninepatchconvert.creation.NinePatchIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConversionBatch
{
    private final Map<String, NinePatchConfig> ninePatchConfigs;

    public ConversionBatch()
    {
        this.ninePatchConfigs = new HashMap<String, NinePatchConfig>();
    }

    public void add(final String filePath, final NinePatchConfig config)
    {
        this.ninePatchConfigs.put(filePath, config);
    }

    public void add(final Map<String, NinePatchConfig> configs)
    {
        this.ninePatchConfigs.putAll(configs);
    }

    public void process(final NinePatchIO ninePatchIO)
    {
        for(final String fileName : ninePatchConfigs.keySet())
        {
            processNinePatchConfigEntry(fileName, ninePatchIO);
        }
    }

    private void processNinePatchConfigEntry(final String filePath, final NinePatchIO ninePatchIO)
    {
        final NinePatchConfig currentConfig = ninePatchConfigs.get(filePath);

        try
        {
            convertAndSaveNinePatch(filePath, currentConfig, ninePatchIO);
        }
        catch(final IOException e)
        {
            System.err.println("Could not process image: " + filePath);
            System.err.println(e.getMessage());
        }
    }

    private void convertAndSaveNinePatch(final String inputImageFilePath, final NinePatchConfig config,
                                         final NinePatchIO ninePatchIO)
            throws IOException
    {
        final BufferedImage inputImage = ninePatchIO.read(inputImageFilePath);
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        ninePatchIO.writeNinePatchFor(ninePatch, inputImageFilePath);
    }
}
