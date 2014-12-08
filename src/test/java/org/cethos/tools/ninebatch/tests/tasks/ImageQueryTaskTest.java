package org.cethos.tools.ninebatch.tests.tasks;

import org.cethos.tools.ninebatch.tasks.ConversionFailureException;
import org.cethos.tools.ninebatch.tasks.ImageQueryTask;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ImageQueryTaskTest
{
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final PrintStream oldStdOut = System.out;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testProcess_noConversions()
    {
        final StreamProvider streamProvider = new ResourceStreamProvider("/envs/nofiles");
        final ImageQueryTask imageQueryTask = new ImageQueryTask(streamProvider);
        thrown.expect(ConversionFailureException.class);
        imageQueryTask.process();
    }

    @Test
    public void testProcess_oneConversion()
    {
        final StreamProvider streamProvider = new ResourceStreamProvider("/envs/oneimage");
        final ImageQueryTask imageQueryTask = new ImageQueryTask(streamProvider);
        imageQueryTask.process();
        assertEquals("testimage.png" + NEW_LINE, outContent.toString());
    }

    @Test
    public void testProcess_twoConversions()
    {
        final StreamProvider streamProvider = new ResourceStreamProvider("/envs/twoimages");
        final ImageQueryTask imageQueryTask = new ImageQueryTask(streamProvider);
        imageQueryTask.process();
        assertEquals("testimage2.png" + NEW_LINE + "testimage.png" + NEW_LINE, outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(oldStdOut);
    }
}
