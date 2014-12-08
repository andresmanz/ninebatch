package org.cethos.tools.ninebatch.tasks;

import org.cethos.tools.ninebatch.tasks.batch.ConversionBatch;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.util.Map;
import java.util.Set;

public class BatchConversionTask implements Task
{
    private final StreamProvider streamProvider;
    private boolean isDeletingImageSourcesEnabled;

    public BatchConversionTask(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public void setDeletingImageSourcesEnabled(final boolean isEnabled)
    {
        this.isDeletingImageSourcesEnabled = isEnabled;
    }

    public void process()
    {
        final ConversionConfigLoader loader = new ConversionConfigLoader(streamProvider);
        final Map<String, NinePatchConfig> conversions = loader.loadFromDefaultConfigFile();
        final ConversionBatch conversionBatch = new ConversionBatch(streamProvider);
        conversionBatch.process(conversions);

        if(isDeletingImageSourcesEnabled)
        {
            deleteOriginalImageSources(conversions.keySet());
        }
    }

    private void deleteOriginalImageSources(final Set<String> fileNames)
    {
        for(final String fileName : fileNames)
        {
            streamProvider.deleteImageSource(fileName);
        }
    }
}
