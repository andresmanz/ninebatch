package org.cethos.tools.ninepatchconvert.creation;

public class NinePatchConfig
{
    public final PixelRange xScalingRange;
    public final PixelRange yScalingRange;
    public final PixelRange xPaddingRange;
    public final PixelRange yPaddingRange;

    public NinePatchConfig()
    {
        this.xScalingRange = new PixelRange();
        this.yScalingRange = new PixelRange();
        this.xPaddingRange = new PixelRange();
        this.yPaddingRange = new PixelRange();
    }
}