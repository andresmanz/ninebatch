package org.cethos.tools.ninebatch.conversion;

import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;
import org.cethos.tools.ninebatch.util.NinePatchUtil;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StreamConversionIO
{

    private final StreamProvider streamProvider;

    public StreamConversionIO(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public void writeNinePatchFor(final RenderedImage image, final String originalFileName) throws IOException
    {
        final String ninePatchFileName = NinePatchUtil.getNinePatchFileNameFor(originalFileName);
        ImageIO.write(image, "png", streamProvider.getOutputStreamFor(ninePatchFileName));
    }

    public BufferedImage read(final String imageFilePath) throws IOException
    {
        final InputStream inputStream = streamProvider.getInputStreamFor(imageFilePath);
        final BufferedImage image = ImageIO.read(inputStream);
        inputStream.close();
        return image;
    }
}
