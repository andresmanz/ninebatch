package org.cethos.ninepatch.tests;

import org.apache.commons.io.IOUtils;
import org.cethos.ninepatch.NinePatchConfig;
import org.cethos.ninepatch.JsonConfigParsing;
import org.cethos.ninepatch.PixelRange;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class JsonConfigParsingTest
{
    private static final String RES_PATH_NO_ENTRIES = "/ninepatchconfigs/ninepatches.noentries.json";
    private static final String RES_PATH_TWO_ENTRIES = "/ninepatchconfigs/ninepatches.twoentries.json";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetImageConfigsFromJson_jsonWithoutEntries_shouldReturnListWithoutEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_NO_ENTRIES);
        final List<NinePatchConfig> ninePatchConfigs = JsonConfigParsing.getImageConfigsFromJson(json);
        assertEquals(0, ninePatchConfigs.size());
    }

    @Test
    public void testGetImageConfigsFromJson_jsonWithTwoEntries_shouldReturnListWithTwoEntries() throws IOException
    {
        final String json = getJsonFromResource(RES_PATH_TWO_ENTRIES);
        final List<NinePatchConfig> ninePatchConfigs = JsonConfigParsing.getImageConfigsFromJson(json);

        final NinePatchConfig config1 = new NinePatchConfig();
        config1.setFileName("testimg1.png");
        config1.xScalingRange.set(16, 48);
        config1.yScalingRange.set(16, 48);
        config1.xPaddingRange.set(18, 46);
        config1.yPaddingRange.set(18, 46);

        final NinePatchConfig config2 = new NinePatchConfig();
        config2.setFileName("testimg2.png");
        config2.xScalingRange.set(14, 50);
        config2.yScalingRange.set(14, 50);
        config2.xPaddingRange.set(16, 48);
        config2.yPaddingRange.set(16, 48);

        assertEquals(2, ninePatchConfigs.size());
        assertNinePatchConfigsAreEqual(config1, ninePatchConfigs.get(0));
        assertNinePatchConfigsAreEqual(config2, ninePatchConfigs.get(1));
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
        assertEquals(firstConfig.getFileName(), secondConfig.getFileName());
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
