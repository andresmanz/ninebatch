package org.cethos.ninepatch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class ConversionBatchProcessing
{
    private ConversionBatchProcessing()
    {
    }

    public static void convert(final BatchConfig config, final List<NinePatchConfig> ninePatchConfigs)
    {
        for(final NinePatchConfig ninePatchConfig : ninePatchConfigs)
        {
            try
            {
                convertAndSave(config, ninePatchConfig);
            }
            catch(final IOException e)
            {
                System.err.println("Could not process image: " + e.getMessage());
            }
        }
    }

    private static void convertAndSave(final BatchConfig batchConfig, final NinePatchConfig config) throws IOException
    {
        final BufferedImage ninePatch = NinePatchCreation.createFrom(
                batchConfig.getInputDirPath(), config);
        final String ninePatchFileName = getOutFileNameFor(config.getFileName());
        final String outFilePath = batchConfig.getOutputDirPath() + "/" + ninePatchFileName;
        ImageIO.write(ninePatch, "png", new File(outFilePath));
    }

    private static String getOutFileNameFor(final String inputFileName)
    {
        final StringBuilder stringBuilder = new StringBuilder(inputFileName);
        final int lastPeriodIndex = inputFileName.lastIndexOf(".");
        stringBuilder.insert(lastPeriodIndex, ".9");
        return stringBuilder.toString();
    }
}
