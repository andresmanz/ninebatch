package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.conversion.batch.ConversionBatch;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConversionBatchTest
{
    private ResourceStreamProvider streamProvider;
    private ConversionBatch conversionBatch;

    @Before
    public void beforeTest()
    {
        this.streamProvider = new ResourceStreamProvider("/images");
        this.conversionBatch = new ConversionBatch(streamProvider);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testProcess_withZeroImages()
    {
        conversionBatch.process(new HashMap<String, NinePatchConfig>());
        assertEquals(0, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(0, streamProvider.getTotalOutputStreamAccessCount());
    }

    @Test
    public void testProcess_withOneImage()
    {
        final Map<String, NinePatchConfig> conversions = new HashMap<String, NinePatchConfig>();
        conversions.put("testimage.png", new NinePatchConfig());
        conversionBatch.process(conversions);
        assertEquals(1, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(1, streamProvider.getTotalOutputStreamAccessCount());
    }

    @Test
    public void testProcess_withNonExistentImagePath()
    {
        final Map<String, NinePatchConfig> conversions = new HashMap<String, NinePatchConfig>();
        conversions.put("/thisdoesnotexist.png", new NinePatchConfig());
        conversionBatch.process(conversions);
        assertEquals(1, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(0, streamProvider.getTotalOutputStreamAccessCount());
    }
}
