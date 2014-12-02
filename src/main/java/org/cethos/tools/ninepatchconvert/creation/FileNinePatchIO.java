package org.cethos.tools.ninepatchconvert.creation;

import org.cethos.tools.ninepatchconvert.NinePatchUtil;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileNinePatchIO implements NinePatchIO
{
    private final String baseInputDirectory;
    private final String baseOutputDirectory;

    public FileNinePatchIO(final String baseInputDirectory, final String baseOutputDirectory)
    {
        this.baseInputDirectory = baseInputDirectory;
        this.baseOutputDirectory = baseOutputDirectory;
    }

    @Override
    public void writeNinePatchFor(final RenderedImage image, final String originalFileName) throws IOException
    {
        ImageIO.write(image, "png", getInputFileFor(originalFileName));
    }

    private File getInputFileFor(final String inputFileName)
    {
        return new File(baseInputDirectory, inputFileName);
    }

    @Override
    public BufferedImage read(final String fileName) throws IOException
    {
        return ImageIO.read(getNinePatchFileFor(fileName));
    }

    private File getNinePatchFileFor(final String imageFileName)
    {
        final String ninePatchFileName = NinePatchUtil.getNinePatchFileNameFor(imageFileName);
        return new File(baseOutputDirectory, ninePatchFileName);
    }
}
