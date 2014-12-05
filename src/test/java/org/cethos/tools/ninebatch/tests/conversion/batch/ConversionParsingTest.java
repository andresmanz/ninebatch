package org.cethos.tools.ninebatch.tests.conversion.batch;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.conversion.ConversionParsing;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.PixelRange;
import org.cethos.tools.ninebatch.tests.testutil.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConversionParsingTest
{
    private static final String RES_PATH_NO_ENTRIES = "/ninepatchconfigs/ninepatches.noentries.json";
    private static final String RES_PATH_TWO_ENTRIES = "/ninepatchconfigs/ninepatches.twoentries.json";

    @Test
    public void testConstructorIsPrivate() throws Exception
    {
        Assert.assertConstructorIsPrivate(ConversionParsing.class);
    }

    @Test
    public void testParse_jsonWithoutEntries_shouldReturnListWithoutEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_NO_ENTRIES);
        final Map<String, NinePatchConfig> ninePatchConfigs = ConversionParsing.parse(json);
        assertEquals(0, ninePatchConfigs.size());
    }

    @Test
    public void testParse_jsonWithTwoEntries_shouldReturnListWithTwoEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_TWO_ENTRIES);
        final Map<String, NinePatchConfig> ninePatchConfigs = ConversionParsing.parse(json);

        final NinePatchConfig config1 = new NinePatchConfig();
        config1.getXScalingRange().set(16, 48);
        config1.getYScalingRange().set(16, 48);
        config1.getXPaddingRange().set(18, 46);
        config1.getYPaddingRange().set(18, 46);

        final NinePatchConfig config2 = new NinePatchConfig();
        config2.getXScalingRange().set(14, 50);
        config2.getYScalingRange().set(14, 50);
        config2.getXPaddingRange().set(16, 48);
        config2.getYPaddingRange().set(16, 48);

        assertEquals(2, ninePatchConfigs.size());
        assertNinePatchConfigsAreEqual(config1, ninePatchConfigs.get("testimg1.png"));
        assertNinePatchConfigsAreEqual(config2, ninePatchConfigs.get("testimg2.png"));
    }

    private String getJsonFromResource(final String resourcePath) throws IOException
    {
        final InputStream resourceStream = getClass().getResourceAsStream(resourcePath);
        final String json = IOUtils.toString(resourceStream, "UTF-8");
        return json;
    }

    private static void assertNinePatchConfigsAreEqual(final NinePatchConfig firstConfig,
                                                final NinePatchConfig secondConfig)
    {
        assertPixelRangesAreEqual(firstConfig.getXScalingRange(), secondConfig.getXScalingRange());
        assertPixelRangesAreEqual(firstConfig.getYScalingRange(), secondConfig.getYScalingRange());
        assertPixelRangesAreEqual(firstConfig.getXPaddingRange(), secondConfig.getXPaddingRange());
        assertPixelRangesAreEqual(firstConfig.getYPaddingRange(), secondConfig.getYPaddingRange());
    }

    private static void assertPixelRangesAreEqual(final PixelRange firstRange,
                                           final PixelRange secondRange)
    {
        assertEquals(firstRange.getBegin(), secondRange.getBegin());
        assertEquals(firstRange.getEnd(), secondRange.getEnd());
    }
}
