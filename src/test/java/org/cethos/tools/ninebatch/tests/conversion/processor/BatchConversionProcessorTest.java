package org.cethos.tools.ninebatch.tests.conversion.processor;

import org.cethos.tools.ninebatch.conversion.ConversionFailureException;
import org.cethos.tools.ninebatch.conversion.processor.BatchConversionProcessor;
import org.cethos.tools.ninebatch.tests.conversion.batch.ResourceStreamProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BatchConversionProcessorTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadAndProcess_withNonExistingInputDirectory()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/doesnotexist");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        thrown.expect(ConversionFailureException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withoutFilesInInputDirectory()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/nofiles");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        thrown.expect(ConversionFailureException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withOneImage()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/oneimage");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        processor.loadAndProcessConversions();
        assertEquals(2, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(1, streamProvider.getTotalOutputStreamAccessCount());
    }
}
