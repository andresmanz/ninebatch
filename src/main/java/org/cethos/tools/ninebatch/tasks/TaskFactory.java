package org.cethos.tools.ninebatch.tasks;

import org.cethos.tools.ninebatch.tasks.io.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;

public class TaskFactory
{
    public Task createFrom(final String[] args)
    {
        final ArgumentParser argumentParser = new ArgumentParser();
        final RunConfig runConfig = argumentParser.createConfigFrom(args);
        final StreamProvider streamProvider = createStreamProviderFrom(runConfig);

        Task task;

        if(runConfig.isQueryRequested())
        {
            task = new ImageQueryTask(streamProvider);
        }
        else
        {
            task = new BatchConversionTask(streamProvider);
        }

        return task;
    }

    private static StreamProvider createStreamProviderFrom(final RunConfig runConfig)
    {
        final String inputDir = runConfig.getInputDirPath();
        final String outputDir = runConfig.getOutputDirPath();
        return new RelativeFileStreamProvider(inputDir, outputDir);
    }
}
