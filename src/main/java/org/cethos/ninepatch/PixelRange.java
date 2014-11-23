package org.cethos.ninepatch;

public class PixelRange
{
    private int begin;
    private int end;

    public PixelRange(final int begin, final int end)
    {
        set(begin, end);
    }

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
    }
}
