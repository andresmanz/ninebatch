package org.cethos.tools.ninepatchconvert.tests.creation;

import org.cethos.tools.ninepatchconvert.creation.PixelRange;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class PixelRangeTest
{
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSet_withNormalRange()
    {
        final PixelRange pixelRange = new PixelRange();
        assertFalse(pixelRange.isSet());
        pixelRange.set(5, 10);
        assertTrue(pixelRange.isSet());
    }

    @Test
    public void testSet_withEndLessThanBegin()
    {
        final PixelRange pixelRange = new PixelRange();
        thrown.expect(IllegalArgumentException.class);
        pixelRange.set(10, 2);
    }
}
