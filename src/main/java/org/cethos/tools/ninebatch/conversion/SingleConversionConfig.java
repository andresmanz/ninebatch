package org.cethos.tools.ninebatch.conversion;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;

public class SingleConversionConfig
{
    private String inputFilePath;
    private NinePatchConfig ninePatchConfig;

    public String getInputFilePath()
    {
        return inputFilePath;
    }

    public void setInputFilePath(final String inputFilePath)
    {
        this.inputFilePath = inputFilePath;
    }

    public NinePatchConfig getNinePatchConfig()
    {
        return ninePatchConfig;
    }

    public void setNinePatchConfig(final NinePatchConfig ninePatchConfig)
    {
        this.ninePatchConfig = ninePatchConfig;
    }
}
