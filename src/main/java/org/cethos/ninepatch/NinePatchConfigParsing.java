package org.cethos.ninepatch;

import com.esotericsoftware.jsonbeans.JsonReader;
import com.esotericsoftware.jsonbeans.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class NinePatchConfigParsing
{
    private NinePatchConfigParsing()
    {
    }

    public static List<NinePatchConfig> getImageConfigsFromJson(final String jsonConfig)
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

    private static List<NinePatchConfig> getNinePatchConfigsFrom(final JsonValue ninePatchesValue)
    {
        final List<NinePatchConfig> ninePatchConfigs = new ArrayList<NinePatchConfig>();
        for(final JsonValue ninePatchValue : ninePatchesValue.iterator())
        {
            ninePatchConfigs.add(getSingleNinePatchConfigFrom(ninePatchValue));
        }

        return ninePatchConfigs;
    }

    private static NinePatchConfig getSingleNinePatchConfigFrom(final JsonValue ninePatchValue)
    {
        final NinePatchConfig ninePatchConfig = new NinePatchConfig();
        ninePatchConfig.setFileName(ninePatchValue.getString("inputFile"));
        ninePatchConfig.xScalingRange.set(getPixelRange("xScalingRange", ninePatchValue));
        ninePatchConfig.yScalingRange.set(getPixelRange("yScalingRange", ninePatchValue));
        ninePatchConfig.xPaddingRange.set(getPixelRange("xPaddingRange", ninePatchValue));
        ninePatchConfig.yPaddingRange.set(getPixelRange("yPaddingRange", ninePatchValue));
        return ninePatchConfig;
    }

    private static PixelRange getPixelRange(final String key, final JsonValue ninePatchValue)
    {
        final String rangeString = ninePatchValue.getString(key, "0-0");
        return parsePixelRange(rangeString);
    }

    private static PixelRange parsePixelRange(final String pixelRangeString)
    {
        final String[] splitRangeStrings = pixelRangeString.split("-");
        final int begin = Integer.parseInt(splitRangeStrings[0]);
        final int end = Integer.parseInt(splitRangeStrings[1]);
        return new PixelRange(begin, end);
    }
}
