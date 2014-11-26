package org.cethos.tools.ninepatchconvert.batch;

import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchCreation;

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
            System.err.println("Could not process image: " + e.getMessage());
        }
    }

    private void convertAndSaveNinePatch(final String inputFileName, final NinePatchConfig config)
            throws IOException
    {
        final BufferedImage inputImage = ImageIO.read(getInputFileFor(inputFileName));
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        ImageIO.write(ninePatch, "png", getNinePatchFileFor(inputFileName));
    }

    private File getInputFileFor(final String inputFileName)
    {
        final File inputFile = new File(batchConfig.getInputDirPath(), inputFileName);
        return inputFile;
    }

    private File getNinePatchFileFor(final String inputFileName)
    {
        final String ninePatchFileName = getNinePatchFileNameFor(inputFileName);
        final File ninePatchFile = new File(batchConfig.getOutputDirPath(), ninePatchFileName);
        return ninePatchFile;
    }

    private static String getNinePatchFileNameFor(final String inputFileName)
    {
        final StringBuilder stringBuilder = new StringBuilder(inputFileName);
        final int lastPeriodIndex = inputFileName.lastIndexOf(".");
        stringBuilder.insert(lastPeriodIndex, ".9");
        return stringBuilder.toString();
    }
}
