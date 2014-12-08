package org.cethos.tools.ninebatch.tasks;

import org.cethos.tools.ninebatch.tasks.io.RelativeFileStreamProvider;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;

public class TaskFactory
{
    public Task createFrom(final String[] args)
    {
        final ArgumentParser argumentParser = new ArgumentParser();
        final RunConfig runConfig = argumentParser.createConfigFrom(args);

        Task task;

        if(runConfig.isQueryRequested())
        {
            task = createImageQueryTaskFrom(runConfig);
        }
        else
        {
            task = createBatchConversionTaskFrom(runConfig);
        }

        return task;
    }

    private static Task createBatchConversionTaskFrom(final RunConfig runConfig)
    {
        final StreamProvider streamProvider = createStreamProviderFrom(runConfig);
        final BatchConversionTask task = new BatchConversionTask(streamProvider);
        task.setDeletingImageSourcesEnabled(runConfig.isDeletingOriginalsEnabled());
        return task;
    }

    private static Task createImageQueryTaskFrom(final RunConfig runConfig)
    {
        final StreamProvider streamProvider = createStreamProviderFrom(runConfig);
        return new ImageQueryTask(streamProvider);
    }

    private static StreamProvider createStreamProviderFrom(final RunConfig runConfig)
    {
        final String inputDir = runConfig.getInputDirPath();
        final String outputDir = runConfig.getOutputDirPath();
        return new RelativeFileStreamProvider(inputDir, outputDir);
    }
}
