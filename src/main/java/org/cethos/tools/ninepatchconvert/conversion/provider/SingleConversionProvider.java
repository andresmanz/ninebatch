package org.cethos.tools.ninepatchconvert.conversion.provider;

import org.cethos.tools.ninepatchconvert.conversion.SingleConversionConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SingleConversionProvider implements ConversionProvider
{
    private final SingleConversionConfig config;

    public SingleConversionProvider(final SingleConversionConfig config)
    {
        this.config = config;
    }

    @Override
    public Map<String, NinePatchConfig> createConfigs() throws IOException
    {
        final Map<String, NinePatchConfig> configs = new HashMap<String, NinePatchConfig>();
        configs.put(config.getInputFilePath(), config.getNinePatchConfig());
        return configs;
    }
}
