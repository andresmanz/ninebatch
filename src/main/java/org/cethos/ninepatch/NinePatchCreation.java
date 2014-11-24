package org.cethos.ninepatch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NinePatchCreation
{
    private static final int NINE_PATCH_FORMAT = BufferedImage.TYPE_INT_ARGB;

    private NinePatchCreation()
    {
    }

    // TODO passing image dir is quite ugly - class needs refactoring anyway
    // Keep file name out of NinePatchConfig and use a HashMap instead.
    // Then pass the full path to this method.
    public static BufferedImage createFrom(final String basePath,
                                           final NinePatchConfig config) throws IOException
    {
        final String fullPath = basePath + config.getFileName();
        final BufferedImage inputImage = ImageIO.read(new File(fullPath));
        return createNinePatchFrom(inputImage, config);
    }

    public static BufferedImage createNinePatchFrom(final BufferedImage inputImage,
                                                    final NinePatchConfig config) throws IOException
    {
        final BufferedImage ninePatch = createNinePatchImageFor(inputImage);
        Graphics2D graphics = ninePatch.createGraphics();
        graphics.drawImage(inputImage, 1, 1, null);
        drawRangesTo(graphics, config);
        return ninePatch;
    }

    private static BufferedImage createNinePatchImageFor(final BufferedImage inputImage)
    {
        final int width = inputImage.getWidth() + 2;
        final int height = inputImage.getHeight() + 2;
        final BufferedImage ninePatch = new BufferedImage(width, height, NINE_PATCH_FORMAT);
        return ninePatch;
    }

    private static void drawRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        graphics.setColor(Color.black);
        drawScalingRangesTo(graphics, config);
        drawPaddingRangesTo(graphics, config);
    }

    private static void drawScalingRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        final PixelRange xScalingRange = config.xScalingRange;
        final PixelRange yScalingRange = config.yScalingRange;
        graphics.drawLine(xScalingRange.getBegin(), 0, xScalingRange.getEnd(), 0);
        graphics.drawLine(0, yScalingRange.getBegin(), 0, yScalingRange.getEnd());
    }

    private static void drawPaddingRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        final PixelRange xPaddingRange = config.xPaddingRange;
        final PixelRange yPaddingRange = config.yPaddingRange;
        final int yForX = graphics.getDeviceConfiguration().getBounds().width - 1;
        final int xForY = graphics.getDeviceConfiguration().getBounds().height - 1;
        graphics.drawLine(xPaddingRange.getBegin(), yForX, xPaddingRange.getEnd(), yForX);
        graphics.drawLine(xForY, yPaddingRange.getBegin(), xForY, yPaddingRange.getEnd());
    }
}
