package org.cethos.tools.ninebatch.tasks;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.tasks.batch.ConversionBatch;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;

import java.util.Map;

public class BatchConversionTask implements Task
{
    private final StreamProvider streamProvider;

    public BatchConversionTask(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public void process()
    {
        final ConversionConfigLoader loader = new ConversionConfigLoader(streamProvider);
        final Map<String, NinePatchConfig> conversions = loader.loadFromDefaultConfigFile();
        final ConversionBatch conversionBatch = new ConversionBatch(streamProvider);
        conversionBatch.process(conversions);
    }
}
