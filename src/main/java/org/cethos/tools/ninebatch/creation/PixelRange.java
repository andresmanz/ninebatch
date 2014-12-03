package org.cethos.tools.ninebatch.creation;

public class PixelRange
{
    private int begin;
    private int end;
    private boolean isSet;

    public int getEnd()
    {
        return end;
    }

    public int getBegin()
    {
        return begin;
    }

    public void set(final PixelRange pixelRange)
    {
        set(pixelRange.begin, pixelRange.end);
    }

    public void set(final int begin, final int end)
    {
        if(end < begin)
        {
            throw new IllegalArgumentException("end must not be smaller than begin");
        }

        this.begin = begin;
        this.end = end;
        this.isSet = true;
    }

    public boolean isSet()
    {
        return isSet;
    }
}
