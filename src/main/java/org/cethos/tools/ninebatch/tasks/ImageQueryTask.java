package org.cethos.tools.ninebatch.tasks;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;

import java.util.Map;

public class ImageQueryTask implements Task
{
    private final StreamProvider streamProvider;

    public ImageQueryTask(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    @Override
    public void process()
    {
        final ConversionConfigLoader loader = new ConversionConfigLoader(streamProvider);
        final Map<String, NinePatchConfig> conversions = loader.loadFromDefaultConfigFile();

        for(final String fileName : conversions.keySet())
        {
            System.out.println(fileName);
        }
    }
}
