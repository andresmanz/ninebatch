package org.cethos.tools.ninebatch.tests.batch;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.conversion.ConversionParsing;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.PixelRange;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class NinePatchConfigParsingTest
{
    private static final String RES_PATH_NO_ENTRIES = "/ninepatchconfigs/ninepatches.noentries.json";
    private static final String RES_PATH_TWO_ENTRIES = "/ninepatchconfigs/ninepatches.twoentries.json";

    @Test
    public void testGetImageConfigsFromJson_jsonWithoutEntries_shouldReturnListWithoutEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_NO_ENTRIES);
        final Map<String, NinePatchConfig> ninePatchConfigs =
                ConversionParsing.parse(json);
        assertEquals(0, ninePatchConfigs.size());
    }

    @Test
    public void testGetImageConfigsFromJson_jsonWithTwoEntries_shouldReturnListWithTwoEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_TWO_ENTRIES);
        final Map<String, NinePatchConfig> ninePatchConfigs =
                ConversionParsing.parse(json);

        final NinePatchConfig config1 = new NinePatchConfig();
        config1.xScalingRange.set(16, 48);
        config1.yScalingRange.set(16, 48);
        config1.xPaddingRange.set(18, 46);
        config1.yPaddingRange.set(18, 46);

        final NinePatchConfig config2 = new NinePatchConfig();
        config2.xScalingRange.set(14, 50);
        config2.yScalingRange.set(14, 50);
        config2.xPaddingRange.set(16, 48);
        config2.yPaddingRange.set(16, 48);

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

    private void assertNinePatchConfigsAreEqual(final NinePatchConfig firstConfig,
                                                final NinePatchConfig secondConfig)
    {
        assertPixelRangesAreEqual(firstConfig.xScalingRange, secondConfig.xScalingRange);
        assertPixelRangesAreEqual(firstConfig.yScalingRange, secondConfig.yScalingRange);
        assertPixelRangesAreEqual(firstConfig.xPaddingRange, secondConfig.xPaddingRange);
        assertPixelRangesAreEqual(firstConfig.yPaddingRange, secondConfig.yPaddingRange);
    }

    private void assertPixelRangesAreEqual(final PixelRange firstRange,
                                           final PixelRange secondRange)
    {
        assertEquals(firstRange.getBegin(), secondRange.getBegin());
        assertEquals(firstRange.getEnd(), secondRange.getEnd());
    }
}
