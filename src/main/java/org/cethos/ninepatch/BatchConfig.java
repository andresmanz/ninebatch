package org.cethos.ninepatch;

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

    public String getInputPathFor(final String fileName)
    {
        return inputDirPath + "/" + fileName;
    }

    public String getOutputDirPath()
    {
        return outputDirPath;
    }
}
