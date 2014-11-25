package org.cethos.ninepatch.tests.creation;

import org.cethos.ninepatch.creation.NinePatchConfig;
import org.cethos.ninepatch.creation.NinePatchCreation;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NinePatchCreationTest
{
    @Test
    public void testCreateNinePatchFrom_withTestImage() throws IOException
    {
        final BufferedImage inputImage = loadImageResource("/images/testimage.png");
        final BufferedImage loadedPatch = loadImageResource("/images/testimage.9.png");

        final NinePatchConfig ninePatchConfig = new NinePatchConfig();
        ninePatchConfig.xScalingRange.set(8, 57);
        ninePatchConfig.yScalingRange.set(8, 57);

        final BufferedImage resultPatch = NinePatchCreation.createFrom(inputImage, ninePatchConfig);
        assertImagesEqual(loadedPatch, resultPatch);
    }

    private BufferedImage loadImageResource(final String resourcePath) throws IOException
    {
        final BufferedImage image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
        return image;
    }

    private static void assertImagesEqual(final BufferedImage expected, final BufferedImage actual)
    {
        final int actualWidth = actual.getWidth();
        final int actualHeight = actual.getHeight();
        boolean imagesEqual = true;

        if(expected.getWidth() == actualWidth && expected.getHeight() == actualHeight)
        {
            for(int x = 0; imagesEqual == true && x < actualWidth; ++x)
            {
                for(int y = 0; imagesEqual == true && y < actualHeight; ++y)
                {
                    if(expected.getRGB(x, y) != actual.getRGB(x, y))
                    {
                        imagesEqual = false;
                    }
                }
            }
        }
        else
        {
            imagesEqual = false;
        }

        assertTrue("ninepatches should equal", imagesEqual);
    }
}
