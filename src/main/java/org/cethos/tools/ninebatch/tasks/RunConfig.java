package org.cethos.tools.ninebatch.tasks;

public class RunConfig
{
    private String inputDirPath;
    private String outputDirPath;
    private boolean isQueryRequested;

    public RunConfig(final String inputDirPath, final String outputDirPath)
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

    public boolean isQueryRequested()
    {
        return isQueryRequested;
    }

    public void setQueryRequested(final boolean isQueryRequested)
    {
        this.isQueryRequested = isQueryRequested;
    }
}
