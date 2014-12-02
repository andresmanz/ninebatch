package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;

public class SingleConversionConfig
{
    private String inputFilePath;
    private String outputFilePath;
    private NinePatchConfig ninePatchConfig;

    public String getInputFilePath()
    {
        return inputFilePath;
    }

    public void setInputFilePath(final String inputFilePath)
    {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath()
    {
        return outputFilePath;
    }

    public void setOutputFilePath(final String outputFilePath)
    {
        this.outputFilePath = outputFilePath;
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
