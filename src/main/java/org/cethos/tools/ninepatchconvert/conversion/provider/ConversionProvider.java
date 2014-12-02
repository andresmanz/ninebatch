package org.cethos.tools.ninepatchconvert.conversion.provider;

import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;

import java.io.IOException;
import java.util.Map;

public interface ConversionProvider
{
    public Map<String, NinePatchConfig> createConfigs() throws IOException;
}
