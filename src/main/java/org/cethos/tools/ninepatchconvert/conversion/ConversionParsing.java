package org.cethos.tools.ninepatchconvert.conversion;

import com.esotericsoftware.jsonbeans.JsonReader;
import com.esotericsoftware.jsonbeans.JsonValue;

import org.cethos.tools.ninepatchconvert.NinePatchOptionUtil;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.PixelRange;

import java.util.HashMap;
import java.util.Map;

public class ConversionParsing
{
    private ConversionParsing()
    {
    }

    public static Map<String, NinePatchConfig> parse(final String jsonConfig)
    {
        final JsonValue ninePatchesValue = getNinePatchesValueFrom(jsonConfig);
        return getNinePatchConfigsFrom(ninePatchesValue);
    }

    private static JsonValue getNinePatchesValueFrom(final String jsonConfig)
    {
        final JsonReader jsonReader = new JsonReader();
        final JsonValue root = jsonReader.parse(jsonConfig);
        final JsonValue ninePatchesValue = root.get("ninePatches");
        return ninePatchesValue;
    }

    private static Map<String, NinePatchConfig> getNinePatchConfigsFrom(final JsonValue ninePatchesValue)
    {
        final Map<String, NinePatchConfig> ninePatchConfigs = new HashMap<String, NinePatchConfig>();
        for(final JsonValue ninePatchValue : ninePatchesValue.iterator())
        {
            final NinePatchConfig config = getSingleNinePatchConfigFrom(ninePatchValue);
            ninePatchConfigs.put(ninePatchValue.name(), config);
        }

        return ninePatchConfigs;
    }

    private static NinePatchConfig getSingleNinePatchConfigFrom(final JsonValue ninePatchValue)
    {
        final NinePatchConfig ninePatchConfig = new NinePatchConfig();
        ninePatchConfig.xScalingRange.set(getPixelRangeByKeyFrom("xScalingRange", ninePatchValue));
        ninePatchConfig.yScalingRange.set(getPixelRangeByKeyFrom("yScalingRange", ninePatchValue));
        ninePatchConfig.xPaddingRange.set(getPixelRangeByKeyFrom("xPaddingRange", ninePatchValue));
        ninePatchConfig.yPaddingRange.set(getPixelRangeByKeyFrom("yPaddingRange", ninePatchValue));
        return ninePatchConfig;
    }

    private static PixelRange getPixelRangeByKeyFrom(final String key, final JsonValue ninePatchValue)
    {
        final String rangeString = ninePatchValue.getString(key, "0-0");
        return NinePatchOptionUtil.parsePixelRange(rangeString);
    }
}
