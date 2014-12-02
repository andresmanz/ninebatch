package org.cethos.tools.ninepatchconvert.tests.batch;

import org.cethos.tools.ninepatchconvert.batch.ConversionBatch;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ConversionBatchTest
{
    @Test
    public void testProcess_withZeroImages()
    {
        final MockNinePatchIO imageInputOutput = new MockNinePatchIO();
        final ConversionBatch conversionBatch = new ConversionBatch(imageInputOutput);
        conversionBatch.process(new HashMap<String, NinePatchConfig>());
        assertEquals(0, imageInputOutput.getTotalReadCount());
        assertEquals(0, imageInputOutput.getTotalWriteCount());
    }

    @Test
    public void testProcess_withTwoImages()
    {
        final MockNinePatchIO imageInputOutput = new MockNinePatchIO();
        final ConversionBatch conversionBatch = new ConversionBatch(imageInputOutput);

        final HashMap<String, NinePatchConfig> configs = new HashMap<String, NinePatchConfig>();
        configs.put("testfile1.png", new NinePatchConfig());
        configs.put("testfile2.png", new NinePatchConfig());
        conversionBatch.process(configs);

        assertEquals(2, imageInputOutput.getTotalReadCount());
        assertEquals(2, imageInputOutput.getTotalWriteCount());
    }
}
