package org.cethos.tools.ninebatch.conversion;

public class BatchConfig
{
    private String inputDirPath;
    private String outputDirPath;
    private boolean isDeletingOriginalsEnabled;
    private boolean isQueryRequested;

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
