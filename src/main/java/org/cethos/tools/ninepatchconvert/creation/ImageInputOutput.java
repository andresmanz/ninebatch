package org.cethos.tools.ninepatchconvert.creation;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageInputOutput
{
    public void write(final RenderedImage image, final Path filePath) throws IOException;
    public BufferedImage read(final Path filePath) throws IOException;
}
