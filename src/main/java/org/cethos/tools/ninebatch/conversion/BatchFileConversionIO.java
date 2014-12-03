package org.cethos.tools.ninebatch.conversion;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;
import org.cethos.tools.ninebatch.util.NinePatchUtil;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BatchFileConversionIO implements ConversionIO
{
    private static final String CONVERSION_CONFIG_FILE_NAME = "ninepatches.json";

    private final BatchConfig batchConfig;

    public BatchFileConversionIO(final BatchConfig batchConfig)
    {
        this.batchConfig = batchConfig;
    }

    @Override
    public void writeNinePatchFor(final RenderedImage image, final String originalFileName) throws IOException
    {
        ImageIO.write(image, "png", getNinePatchFileFor(originalFileName));
    }

    private File getNinePatchFileFor(final String imageFilePath)
    {
        final String ninePatchFileName = NinePatchUtil.getNinePatchFileNameFor(imageFilePath);
        return new File(batchConfig.getOutputDirPath(), ninePatchFileName);
    }

    @Override
    public BufferedImage read(final String imageFilePath) throws IOException
    {
        return ImageIO.read(getInputFileFor(imageFilePath));
    }

    @Override
    public String loadConversionJson() throws IOException
    {
        final File configFile = new File(batchConfig.getInputDirPath(), CONVERSION_CONFIG_FILE_NAME);
        final FileInputStream inputStream = new FileInputStream(configFile);
        final String json = IOUtils.toString(inputStream);
        inputStream.close();
        return json;
    }

    private File getInputFileFor(final String inputFileName)
    {
        return new File(batchConfig.getInputDirPath(), inputFileName);
    }
}
