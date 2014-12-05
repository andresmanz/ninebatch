package org.cethos.tools.ninebatch.tests.creation;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.NinePatchCreation;
import org.cethos.tools.ninebatch.tests.testutil.Assert;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import static org.junit.Assert.assertTrue;

public class NinePatchCreationTest
{
    @Test
    public void testConstructorIsPrivate() throws Exception
    {
        Assert.assertConstructorIsPrivate(NinePatchCreation.class);
    }

    @Test
    public void testCreateNinePatchFrom_withTestImage() throws IOException
    {
        final BufferedImage inputImage = loadImageResource("/images/testimage.png");
        final BufferedImage loadedPatch = loadImageResource("/images/testimage.9.png");

        final NinePatchConfig ninePatchConfig = new NinePatchConfig();
        ninePatchConfig.getXScalingRange().set(8, 57);
        ninePatchConfig.getYScalingRange().set(8, 57);

        final RenderedImage resultPatch = NinePatchCreation.createFrom(inputImage, ninePatchConfig);
        assertImagesEqual(loadedPatch, resultPatch);
    }

    private BufferedImage loadImageResource(final String resourcePath) throws IOException
    {
        final BufferedImage image = ImageIO.read(getClass().getResourceAsStream(resourcePath));
        return image;
    }

    private static void assertImagesEqual(final RenderedImage expected, final RenderedImage actual)
    {
        DataBuffer dbActual = actual.getData().getDataBuffer();
        DataBuffer dbExpected = expected.getData().getDataBuffer();

        DataBufferByte actualDBAsDBInt = (DataBufferByte)dbActual;
        DataBufferByte expectedDBAsDBInt = (DataBufferByte)dbExpected;

        for(int bank = 0; bank < actualDBAsDBInt.getNumBanks(); ++bank)
        {
            byte[] actualBytes = actualDBAsDBInt.getData(bank);
            byte[] expectedBytes = expectedDBAsDBInt.getData(bank);
            assertTrue(Arrays.equals(actualBytes, expectedBytes));
        }
    }
}
