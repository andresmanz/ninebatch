package org.cethos.tools.ninebatch.creation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NinePatchCreation
{
    private static final int NINE_PATCH_FORMAT = BufferedImage.TYPE_INT_ARGB;

    private NinePatchCreation()
    {
    }

    public static BufferedImage createFrom(final BufferedImage inputImage,
                                           final NinePatchConfig config) throws IOException
    {
        final BufferedImage ninePatch = createNinePatchWithLineExtensionFor(inputImage);
        Graphics2D graphics = ninePatch.createGraphics();
        graphics.drawImage(inputImage, 1, 1, null);
        drawPixelRangesTo(graphics, config);
        return ninePatch;
    }

    private static BufferedImage createNinePatchWithLineExtensionFor(final BufferedImage inputImage)
    {
        final int width = inputImage.getWidth() + 2;
        final int height = inputImage.getHeight() + 2;
        final BufferedImage ninePatch = new BufferedImage(width, height, NINE_PATCH_FORMAT);
        return ninePatch;
    }

    private static void drawPixelRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        graphics.setColor(Color.BLACK);
        drawScalingRangesTo(graphics, config);
        drawPaddingRangesTo(graphics, config);
    }

    private static void drawScalingRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        drawXRangeIfSet(config.xScalingRange, 0, graphics);
        drawYRangeIfSet(config.yScalingRange, 0, graphics);
    }

    private static void drawPaddingRangesTo(final Graphics2D graphics, final NinePatchConfig config)
    {
        final int yForX = graphics.getDeviceConfiguration().getBounds().width - 1;
        final int xForY = graphics.getDeviceConfiguration().getBounds().height - 1;
        drawXRangeIfSet(config.xPaddingRange, yForX, graphics);
        drawYRangeIfSet(config.yPaddingRange, xForY, graphics);
    }

    private static void drawXRangeIfSet(final PixelRange range, final int y, final Graphics2D graphics)
    {
        if(range.isSet())
        {
            graphics.drawLine(range.getBegin(), y, range.getEnd(), y);
        }
    }

    private static void drawYRangeIfSet(final PixelRange range, final int x, final Graphics2D graphics)
    {
        if(range.isSet())
        {
            graphics.drawLine(x, range.getBegin(), x, range.getEnd());
        }
    }
}
