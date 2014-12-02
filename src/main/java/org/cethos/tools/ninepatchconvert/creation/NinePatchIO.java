package org.cethos.tools.ninepatchconvert.creation;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

public interface NinePatchIO
{
    public void writeNinePatchFor(final RenderedImage image, final String fileName) throws IOException;
    public BufferedImage read(final String fileName) throws IOException;
}
