package org.cethos.tools.ninepatchconvert;

import org.cethos.tools.ninepatchconvert.creation.PixelRange;

public class NinePatchOptionUtil
{
    private NinePatchOptionUtil()
    {
    }

    public static PixelRange parsePixelRange(final String pixelRangeString)
    {
        final String[] splitRangeStrings = pixelRangeString.split("-");
        final int begin = Integer.parseInt(splitRangeStrings[0]);
        final int end = Integer.parseInt(splitRangeStrings[1]);
        final PixelRange pixelRange = new PixelRange();
        pixelRange.set(begin, end);
        return pixelRange;
    }
}
