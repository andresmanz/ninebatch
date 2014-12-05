package org.cethos.tools.ninebatch.creation;

public class NinePatchConfig
{
    private final PixelRange xScalingRange;
    private final PixelRange yScalingRange;
    private final PixelRange xPaddingRange;
    private final PixelRange yPaddingRange;

    public NinePatchConfig()
    {
        this.xScalingRange = new PixelRange();
        this.yScalingRange = new PixelRange();
        this.xPaddingRange = new PixelRange();
        this.yPaddingRange = new PixelRange();
    }

    public PixelRange getXScalingRange()
    {
        return xScalingRange;
    }

    public PixelRange getYScalingRange()
    {
        return yScalingRange;
    }

    public PixelRange getXPaddingRange()
    {
        return xPaddingRange;
    }

    public PixelRange getYPaddingRange()
    {
        return yPaddingRange;
    }
}
