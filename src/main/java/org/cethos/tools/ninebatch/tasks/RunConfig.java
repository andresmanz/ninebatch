package org.cethos.tools.ninebatch.tasks;

public class RunConfig
{
    private String inputDirPath;
    private String outputDirPath;
    private boolean isDeletingOriginalsEnabled;
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

    public boolean isDeletingOriginalsEnabled()
    {
        return isDeletingOriginalsEnabled;
    }

    public void setDeletingOriginalsEnabled(final boolean isEnabled)
    {
        this.isDeletingOriginalsEnabled = isEnabled;
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
