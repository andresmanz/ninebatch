package org.cethos.tools.ninebatch.tests;

import org.cethos.tools.ninebatch.util.NinePatchUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NinePatchUtilTest
{
    @Test
    public void testGetNinePatchFileNameFor_withShortFilePath()
    {
        final String fileName = "fileName.png";
        final String ninePatchName = NinePatchUtil.getNinePatchFileNameFor(fileName);
        assertEquals("fileName.9.png", ninePatchName);
    }

    @Test
    public void testGetNinePatchFileNameFor_withLongFilePath()
    {
        final String fileName = "/this/is/a/long/fileName.png";
        final String ninePatchName = NinePatchUtil.getNinePatchFileNameFor(fileName);
        assertEquals("/this/is/a/long/fileName.9.png", ninePatchName);
    }

    @Test
    public void testGetNinePatchFileNameFor_withoutFileTypeExtension()
    {
        final String fileName = "fileName";
        final String ninePatchName = NinePatchUtil.getNinePatchFileNameFor(fileName);
        assertEquals("fileName.9", ninePatchName);
    }
}
