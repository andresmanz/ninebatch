package org.cethos.tools.ninebatch.conversion.provider;

import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.io.IOException;
import java.util.Map;

public interface ConversionProvider
{
    public Map<String, NinePatchConfig> createConfigs() throws IOException;
}
