package org.cethos.tools.ninebatch.tests;

import org.cethos.tools.ninebatch.DefaultConversionProcessor;
import org.cethos.tools.ninebatch.tests.batch.ResourceStreamProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DefaultConversionProcessorTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadAndProcess_withNonExistingInputDirectory() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/doesnotexist");
        final DefaultConversionProcessor processor = new DefaultConversionProcessor(streamProvider);
        thrown.expect(IOException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withoutFilesInInputDirectory() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/nofiles");
        final DefaultConversionProcessor processor = new DefaultConversionProcessor(streamProvider);
        thrown.expect(IOException.class);
        processor.loadAndProcessConversions();
    }

    @Test
    public void testLoadAndProcess_withOneImage() throws IOException
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/oneimage");
        final DefaultConversionProcessor processor = new DefaultConversionProcessor(streamProvider);
        processor.loadAndProcessConversions();
        assertEquals(2, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(1, streamProvider.getTotalOutputStreamAccessCount());
    }
}
