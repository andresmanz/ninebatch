package org.cethos.ninepatch.tests;

import org.cethos.ninepatch.NinePatchConfig;
import org.cethos.ninepatch.NinePatchCreation;
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
        ninePatchConfig.setFileName("/images/testimage.png");
        ninePatchConfig.xScalingRange.set(8, 57);
        ninePatchConfig.yScalingRange.set(8, 57);

        final BufferedImage resultPatch = NinePatchCreation.createNinePatchFrom(inputImage, ninePatchConfig);
        assertImagesEqual(loadedPatch, resultPatch);
    }

    private BufferedImage loadImageResource(final String resourcePath) throws IOException
    {
        final BufferedImage image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
        return image;
    }

    private static void assertImagesEqual(final BufferedImage expected, final BufferedImage actual)
    {
        /*DataBuffer dbActual = expected.getRaster().getDataBuffer();
        DataBuffer dbExpected = actual.getRaster().getDataBuffer();

        DataBufferByte actualDBAsDBInt = (DataBufferByte) dbActual ;
        DataBufferByte expectedDBAsDBInt = (DataBufferByte) dbExpected ;

        for (int bank = 0; bank < actualDBAsDBInt.getNumBanks(); bank++) {
            byte[] actualData = actualDBAsDBInt.getData(bank);
            byte[] expectedData = expectedDBAsDBInt.getData(bank);
            assertArrayEquals(expectedData, actualData);
        }*/

        int width;
        int height;
        boolean imagesEqual = true;

        if(expected.getWidth() == (width = actual.getWidth()) &&
                expected.getHeight() == (height = actual.getHeight()))
        {
            for(int x = 0; imagesEqual == true && x < width; ++x)
            {
                for(int y = 0; imagesEqual == true && y < height; ++y)
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
