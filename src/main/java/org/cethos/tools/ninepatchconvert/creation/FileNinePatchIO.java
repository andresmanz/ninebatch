package org.cethos.tools.ninepatchconvert.creation;

import org.cethos.tools.ninepatchconvert.NinePatchUtil;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileNinePatchIO implements NinePatchIO
{
    @Override
    public void writeNinePatchFor(final RenderedImage image, final String originalFileName) throws IOException
    {
        ImageIO.write(image, "png", getNinePatchFileFor(originalFileName));
    }

    private File getNinePatchFileFor(final String imageFilePath)
    {
        final String ninePatchFileName = NinePatchUtil.getNinePatchFileNameFor(imageFilePath);
        return new File(ninePatchFileName);
    }

    @Override
    public BufferedImage read(final String imageFilePath) throws IOException
    {
        return ImageIO.read(getInputFileFor(imageFilePath));
    }

    private File getInputFileFor(final String inputFileName)
    {
        return new File(inputFileName);
    }
}
