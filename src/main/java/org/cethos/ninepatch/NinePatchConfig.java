package org.cethos.ninepatch;

public class NinePatchConfig
{
    private String fileName;

    public final PixelRange xScalingRange;
    public final PixelRange yScalingRange;
    public final PixelRange xPaddingRange;
    public final PixelRange yPaddingRange;

    public NinePatchConfig()
    {
        this.xScalingRange = new PixelRange(0, 0);
        this.yScalingRange = new PixelRange(0, 0);
        this.xPaddingRange = new PixelRange(0, 0);
        this.yPaddingRange = new PixelRange(0, 0);
    }

    public void setFileName(final String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
}
