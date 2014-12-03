package org.cethos.tools.ninebatch.tests.conversion.provider;

import org.cethos.tools.ninebatch.conversion.SingleConversionConfig;
import org.cethos.tools.ninebatch.conversion.provider.SingleConversionProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SingleConversionProviderTest
{
    @Test
    public void testCreateConfigs_withTestFileName() throws IOException
    {
        final SingleConversionConfig conversionConfig = new SingleConversionConfig();
        conversionConfig.setInputFilePath("/test/file/path.png");
        conversionConfig.setNinePatchConfig(null);
        final SingleConversionProvider provider = new SingleConversionProvider(conversionConfig);
        final Map<String, NinePatchConfig> configs = provider.createConfigs();
        assertTrue(configs.containsKey("/test/file/path.png"));
    }
}
