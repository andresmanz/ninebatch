package org.cethos.tools.ninebatch.tests.conversion.processor;

import org.cethos.tools.ninebatch.conversion.processor.BatchConversionProcessor;
import org.cethos.tools.ninebatch.tests.conversion.batch.ResourceStreamProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BatchConversionProcessorTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadAndProcess_withNonExistingInputDirectory() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/doesnotexist");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        thrown.expect(IOException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withoutFilesInInputDirectory() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/nofiles");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        thrown.expect(IOException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withOneImage() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/oneimage");
        final BatchConversionProcessor processor = new BatchConversionProcessor(streamProvider);
        processor.loadAndProcessConversions();
        assertEquals(2, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(1, streamProvider.getTotalOutputStreamAccessCount());
    }
}
