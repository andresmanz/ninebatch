package org.cethos.tools.ninepatchconvert.batch;

import org.cethos.tools.ninepatchconvert.NinePatchUtil;
import org.cethos.tools.ninepatchconvert.creation.ImageInputOutput;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchCreation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;

public class ConversionBatch
{
    private final BatchConfig batchConfig;
    private final ImageInputOutput imageInputOutput;

    public ConversionBatch(final BatchConfig batchConfig, final ImageInputOutput imageInputOutput)
    {
        this.batchConfig = batchConfig;
        this.imageInputOutput = imageInputOutput;
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
        final BufferedImage inputImage = imageInputOutput.read(getInputFilePathFor(imageFileName));
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        imageInputOutput.write(ninePatch, getNinePatchFilePathFor(imageFileName));
    }

    private Path getInputFilePathFor(final String inputFileName)
    {
        return Paths.get(batchConfig.getInputDirPath(), inputFileName);
    }

    private Path getNinePatchFilePathFor(final String imageFileName)
    {
        final String ninePatchFileName = NinePatchUtil.getNinePatchFileNameFor(imageFileName);
        return Paths.get(batchConfig.getOutputDirPath(), ninePatchFileName);
    }
}
