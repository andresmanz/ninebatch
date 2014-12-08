package org.cethos.tools.ninebatch.tasks.batch;

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
        final NinePatchConfig config = new NinePatchConfig();

        setPixelRangeByKeyIfSet(config.getXScalingRange(), "xScalingRange", ninePatchValue);
        setPixelRangeByKeyIfSet(config.getYScalingRange(), "yScalingRange", ninePatchValue);
        setPixelRangeByKeyIfSet(config.getXPaddingRange(), "xPaddingRange", ninePatchValue);
        setPixelRangeByKeyIfSet(config.getYPaddingRange(), "yPaddingRange", ninePatchValue);

        return config;
    }

    private static void setPixelRangeByKeyIfSet(final PixelRange pixelRange, final String key,
                                                final JsonValue ninePatchValue)
    {
        if(ninePatchValue.has(key))
        {
            final String rangeString = ninePatchValue.getString(key);
            final PixelRange parsedPixelRange = parsePixelRange(rangeString);
            pixelRange.set(parsedPixelRange);
        }
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
