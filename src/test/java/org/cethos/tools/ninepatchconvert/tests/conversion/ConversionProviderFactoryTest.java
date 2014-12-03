package org.cethos.tools.ninepatchconvert.tests.conversion;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.conversion.ConversionProviderFactory;
import org.cethos.tools.ninepatchconvert.conversion.provider.BatchConfigConversionProvider;
import org.cethos.tools.ninepatchconvert.conversion.provider.ConversionProvider;
import org.cethos.tools.ninepatchconvert.conversion.provider.SingleConversionProvider;
import org.cethos.tools.ninepatchconvert.tests.CommandLineUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ConversionProviderFactoryTest
{
    private ConversionProviderFactory factory;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Before
    public void beforeTest()
    {
        this.factory = new ConversionProviderFactory();
    }

    @Test
    public void testCreateFrom_withoutArguments() throws IOException, ParseException
    {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        factory.createFrom(new String[0]);
    }

    @Test
    public void testCreateFrom_withSingleImagewithoutArguments() throws IOException, ParseException
    {
        thrown.expect(ParseException.class);
        thrown.expectMessage("Missing required option");
        factory.createFrom(CommandLineUtil.getArgsFrom("-s"));
    }

    @Test
    public void testCreateFrom_withSingleImageArguments() throws IOException, ParseException
    {
        final String[] args = CommandLineUtil.getArgsFrom("-s -i /test/image/file.png");
        ConversionProvider provider = factory.createFrom(args);
        assertTrue(provider instanceof SingleConversionProvider);
    }

    @Test
    public void testCreateFrom_withBatchArguments() throws IOException, ParseException
    {
        final String[] args = CommandLineUtil.getArgsFrom("-i /test/image/dir");
        ConversionProvider provider = factory.createFrom(args);
        assertTrue(provider instanceof BatchConfigConversionProvider);
    }
}
