package org.cethos.tools.ninebatch.conversion;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

public interface ConversionIO
{
    public void writeNinePatchFor(final RenderedImage image, final String fileName) throws IOException;
    public BufferedImage read(final String fileName) throws IOException;
    public String loadConversionJson() throws IOException;
}