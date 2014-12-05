package org.cethos.tools.ninebatch.conversion;

import com.esotericsoftware.jsonbeans.JsonReader;
import com.esotericsoftware.jsonbeans.JsonValue;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.creation.PixelRange;

import java.util.HashMap;
import java.util.Map;

public class ConversionParsing
{
    private ConversionParsing()
    {
    }

    public static Map<String, NinePatchConfig> parse(final String jsonConfig)
    {
        final JsonValue conversionsValue = getConversionsValueFrom(jsonConfig);
        return getConversionsFrom(conversionsValue);
    }

    private static JsonValue getConversionsValueFrom(final String jsonConfig)
    {
        final JsonReader jsonReader = new JsonReader();
        final JsonValue root = jsonReader.parse(jsonConfig);
        return root.get("ninePatches");
    }

    private static Map<String, NinePatchConfig> getConversionsFrom(final JsonValue conversionsValue)
    {
        final Map<String, NinePatchConfig> conversions = new HashMap<String, NinePatchConfig>();
        for(final JsonValue ninePatchValue : conversionsValue.iterator())
        {
            final NinePatchConfig config = getNinePatchConfigFrom(ninePatchValue);
            conversions.put(ninePatchValue.name(), config);
        }

        return conversions;
    }

    private static NinePatchConfig getNinePatchConfigFrom(final JsonValue ninePatchValue)
    {
        final NinePatchConfig ninePatchConfig = new NinePatchConfig();
        ninePatchConfig.getXScalingRange().set(getPixelRangeByKeyFrom("xScalingRange", ninePatchValue));
        ninePatchConfig.getYScalingRange().set(getPixelRangeByKeyFrom("yScalingRange", ninePatchValue));
        ninePatchConfig.getXPaddingRange().set(getPixelRangeByKeyFrom("xPaddingRange", ninePatchValue));
        ninePatchConfig.getYPaddingRange().set(getPixelRangeByKeyFrom("yPaddingRange", ninePatchValue));
        return ninePatchConfig;
    }

    private static PixelRange getPixelRangeByKeyFrom(final String key, final JsonValue ninePatchValue)
    {
        final String rangeString = ninePatchValue.getString(key, "0-0");
        return parsePixelRange(rangeString);
    }

    private static PixelRange parsePixelRange(final String pixelRangeString)
    {
        final String[] splitRangeStrings = pixelRangeString.split("-");
        final int begin = Integer.parseInt(splitRangeStrings[0]);
        final int end = Integer.parseInt(splitRangeStrings[1]);
        final PixelRange pixelRange = new PixelRange();
        pixelRange.set(begin, end);
        return pixelRange;
    }
}
