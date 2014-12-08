package org.cethos.tools.ninebatch.tasks;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;
import org.cethos.tools.ninebatch.tasks.batch.ConversionParsing;
import org.cethos.tools.ninebatch.tasks.io.StreamProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConversionConfigLoader
{
    private static final String CONVERSION_CONFIG_FILE_NAME = "ninepatches.json";

    private final StreamProvider streamProvider;

    public ConversionConfigLoader(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public Map<String, NinePatchConfig> loadFromDefaultConfigFile()
    {
        final String conversionJson = loadConversionJson();
        return ConversionParsing.parse(conversionJson);
    }

    private String loadConversionJson()
    {
        try
        {
            final InputStream inputStream = streamProvider.getInputStreamFor(CONVERSION_CONFIG_FILE_NAME);
            final String json = IOUtils.toString(inputStream);
            inputStream.close();
            return json;
        }
        catch(final IOException exception)
        {
            throw new ConversionFailureException(exception);
        }
    }
}
