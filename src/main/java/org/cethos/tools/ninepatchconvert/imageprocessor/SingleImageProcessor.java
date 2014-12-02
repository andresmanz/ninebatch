package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.cethos.tools.ninepatchconvert.NinePatchUtil;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchCreation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SingleImageProcessor implements ImageProcessor
{
    private final SingleConversionConfig conversionConfig;

    public SingleImageProcessor(final SingleConversionConfig conversionConfig)
    {
        this.conversionConfig = conversionConfig;
    }

    @Override
    public void process() throws IOException
    {
        final NinePatchConfig config = conversionConfig.getNinePatchConfig();
        final BufferedImage inputImage = ImageIO.read(new File(conversionConfig.getInputFilePath()));
        final BufferedImage ninePatch = NinePatchCreation.createFrom(inputImage, config);
        ImageIO.write(ninePatch, "png", new File(conversionConfig.getOutputFilePath()));
    }
}
