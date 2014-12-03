package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.batch.ConversionBatch;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ConversionBatchTest
{
    @Test
    public void testProcess_withZeroImages()
    {
        final MockNinePatchIO imageInputOutput = new MockNinePatchIO();
        final ConversionBatch conversionBatch = new ConversionBatch();
        conversionBatch.add(new HashMap<String, NinePatchConfig>());
        conversionBatch.process(imageInputOutput);
        assertEquals(0, imageInputOutput.getTotalReadCount());
        assertEquals(0, imageInputOutput.getTotalWriteCount());
    }

    @Test
    public void testProcess_withTwoImages()
    {
        final MockNinePatchIO imageInputOutput = new MockNinePatchIO();
        final ConversionBatch conversionBatch = new ConversionBatch();

        conversionBatch.add("testfile1.png", new NinePatchConfig());
        conversionBatch.add("testfile2.png", new NinePatchConfig());
        conversionBatch.process(imageInputOutput);

        assertEquals(2, imageInputOutput.getTotalReadCount());
        assertEquals(2, imageInputOutput.getTotalWriteCount());
    }
}
