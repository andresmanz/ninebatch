package org.cethos.tools.ninebatch.util;

import org.apache.commons.io.FilenameUtils;

public class NinePatchUtil
{
    private NinePatchUtil()
    {
    }

    public static String getNinePatchFileNameFor(final String imageFileName)
    {
        final String extension = FilenameUtils.getExtension(imageFileName);
        if(!extension.isEmpty())
        {
            final StringBuilder stringBuilder = new StringBuilder(imageFileName);
            final int extensionIndex = FilenameUtils.indexOfExtension(imageFileName);
            stringBuilder.insert(extensionIndex, ".9");
            return stringBuilder.toString();
        }
        else
        {
            return imageFileName + ".9";
        }
    }
}
