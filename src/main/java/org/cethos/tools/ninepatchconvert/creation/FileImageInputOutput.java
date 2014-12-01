package org.cethos.tools.ninepatchconvert.creation;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class FileImageInputOutput implements ImageInputOutput
{
    @Override
    public void write(final RenderedImage image, final Path filePath) throws IOException
    {
        ImageIO.write(image, "png", filePath.toFile());
    }

    @Override
    public BufferedImage read(final Path filePath) throws IOException
    {
        return ImageIO.read(filePath.toFile());
    }
}
