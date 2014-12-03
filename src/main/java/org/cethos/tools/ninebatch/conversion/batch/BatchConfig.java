package org.cethos.tools.ninebatch.conversion.batch;

public class BatchConfig
{
    private String inputDirPath;
    private String outputDirPath;

    public BatchConfig(final String inputDirPath, final String outputDirPath)
    {
        this.inputDirPath = inputDirPath;
        this.outputDirPath = outputDirPath;
    }

    public String getInputDirPath()
    {
        return inputDirPath;
    }

    public String getOutputDirPath()
    {
        return outputDirPath;
    }
}
