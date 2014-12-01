package org.cethos.tools.ninepatchconvert.tests.batch;

import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.cethos.tools.ninepatchconvert.batch.ConversionBatch;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class ConversionBatchTest
{
    @Test
    public void testProcess_withZeroImages()
    {
        final BatchConfig config = new BatchConfig("test", "test");
        final MockImageInputOutput imageInputOutput = new MockImageInputOutput();
        final ConversionBatch conversionBatch = new ConversionBatch(config, imageInputOutput);
        conversionBatch.process(new HashMap<String, NinePatchConfig>());
        assertEquals(0, imageInputOutput.getTotalReadCount());
        assertEquals(0, imageInputOutput.getTotalWriteCount());
    }

    @Test
    public void testProcess_withTwoImages()
    {
        final BatchConfig config = new BatchConfig("test", "test");
        final MockImageInputOutput imageInputOutput = new MockImageInputOutput();
        final ConversionBatch conversionBatch = new ConversionBatch(config, imageInputOutput);

        final HashMap<String, NinePatchConfig> configs = new HashMap<String, NinePatchConfig>();
        configs.put("testfile1.png", new NinePatchConfig());
        configs.put("testfile2.png", new NinePatchConfig());
        conversionBatch.process(configs);

        assertEquals(2, imageInputOutput.getTotalReadCount());
        assertEquals(2, imageInputOutput.getTotalWriteCount());
    }
}
