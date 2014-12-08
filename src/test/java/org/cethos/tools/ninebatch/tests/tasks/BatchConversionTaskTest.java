package org.cethos.tools.ninebatch.tests.tasks;

import org.cethos.tools.ninebatch.tasks.BatchConversionTask;
import org.cethos.tools.ninebatch.tasks.ConversionFailureException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BatchConversionTaskTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLoadAndProcess_withNonExistingInputDirectory()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/doesnotexist");
        final BatchConversionTask processor = new BatchConversionTask(streamProvider);
        thrown.expect(ConversionFailureException.class);
        processor.process();
    }

    @Test
    public void testLoadAndProcess_withoutFilesInInputDirectory()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/nofiles");
        final BatchConversionTask processor = new BatchConversionTask(streamProvider);
        thrown.expect(ConversionFailureException.class);
        processor.process();
    }

    @Test
    public void testLoadAndProcess_withOneImage()
    {
        final ResourceStreamProvider streamProvider = new ResourceStreamProvider("/envs/oneimage");
        final BatchConversionTask processor = new BatchConversionTask(streamProvider);
        processor.process();
        assertEquals(2, streamProvider.getTotalInputStreamAccessCount());
        assertEquals(1, streamProvider.getTotalOutputStreamAccessCount());
    }
}
