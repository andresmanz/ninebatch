package org.cethos.tools.ninepatchconvert.tests.imageprocessor;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.imageprocessor.BatchImageProcessor;
import org.cethos.tools.ninepatchconvert.imageprocessor.ImageProcessor;
import org.cethos.tools.ninepatchconvert.imageprocessor.ImageProcessorFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class ImageProcessorFactoryTest
{
    private ImageProcessorFactory processorFactory;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.processorFactory = new ImageProcessorFactory();
    }

    @Test
    public void testCreateFrom_withNoArguments() throws ParseException
    {
        final String[] args = new String[0];
        thrown.expect(ParseException.class);
        processorFactory.createFrom(args);
    }

    @Test
    public void testCreateFrom_withInputDirOnly() throws ParseException
    {
        final String[] args = new String[2];
        args[0] = "-i";
        args[1] = "/test/directory";
        createAndAssertIsCorrectInstance(args, BatchImageProcessor.class);
    }

    private void createAndAssertIsCorrectInstance(final String[] args, final Class expectedClass) throws ParseException
    {
        final ImageProcessor imageProcessor = processorFactory.createFrom(args);
        final boolean isCorrectInstance = (expectedClass.isInstance(imageProcessor));
        assertTrue("Expected to be an instance of " + expectedClass.getName(), isCorrectInstance);
    }
}
