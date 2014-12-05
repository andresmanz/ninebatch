package org.cethos.tools.ninebatch.tests.conversion.processor;

import org.cethos.tools.ninebatch.conversion.ConversionFailureException;
import org.cethos.tools.ninebatch.conversion.processor.BatchConversionProcessor;
import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessor;
import org.cethos.tools.ninebatch.conversion.processor.ConversionProcessorFactory;
import org.cethos.tools.ninebatch.tests.testutil.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class ConversionProcessorFactoryTest
{
    private ConversionProcessorFactory processorFactory;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp()
    {
        this.processorFactory = new ConversionProcessorFactory();
    }

    @Test
    public void testCreateFrom_withoutArgs() throws Exception
    {
        thrown.expect(ConversionFailureException.class);
        processorFactory.createFrom(new String[0]);
    }

    @Test
    public void testCreateFrom_withInvalidArgs() throws Exception
    {
        thrown.expect(ConversionFailureException.class);
        processorFactory.createFrom(CommandLineUtil.getArgsFrom("--doesnotexist"));
    }

    @Test
    public void testCreateFrom_withArgsForBatchProcessor() throws Exception
    {
        final String[] args = CommandLineUtil.getArgsFrom("testdirectory");
        final ConversionProcessor processor = processorFactory.createFrom(args);
        assertTrue(processor instanceof BatchConversionProcessor);
    }
}
