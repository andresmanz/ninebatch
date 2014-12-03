package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.conversion.batch.ConversionBatch;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConversionBatchTest
{
    @Test
    public void testProcess_withZeroImages()
    {
        final MockConversionIO imageInputOutput = new MockConversionIO();
        final ConversionBatch conversionBatch = new ConversionBatch(imageInputOutput);
        conversionBatch.process(new HashMap<String, NinePatchConfig>());
        assertEquals(0, imageInputOutput.getTotalReadCount());
        assertEquals(0, imageInputOutput.getTotalWriteCount());
    }

    @Test
    public void testProcess_withTwoImages()
    {
        final MockConversionIO imageInputOutput = new MockConversionIO();
        final ConversionBatch conversionBatch = new ConversionBatch(imageInputOutput);

        final Map<String, NinePatchConfig> conversions = new HashMap<String, NinePatchConfig>();
        conversions.put("testfile1.png", new NinePatchConfig());
        conversions.put("testfile2.png", new NinePatchConfig());
        conversionBatch.process(conversions);

        assertEquals(2, imageInputOutput.getTotalReadCount());
        assertEquals(2, imageInputOutput.getTotalWriteCount());
    }
}
