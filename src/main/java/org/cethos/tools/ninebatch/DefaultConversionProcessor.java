package org.cethos.tools.ninebatch;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.conversion.ConversionParsing;
import org.cethos.tools.ninebatch.conversion.batch.ConversionBatch;
import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class DefaultConversionProcessor
{
    private static final String CONVERSION_CONFIG_FILE_NAME = "ninepatches.json";

    private final StreamProvider streamProvider;

    public DefaultConversionProcessor(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public void loadAndProcessConversions() throws IOException
    {
        final Map<String, NinePatchConfig> conversions = loadConversions();
        final ConversionBatch conversionBatch = new ConversionBatch(streamProvider);
        conversionBatch.process(conversions);
    }

    private Map<String, NinePatchConfig> loadConversions() throws IOException
    {
        final String conversionJson = loadConversionJson();
        final Map<String, NinePatchConfig> conversions = ConversionParsing.parse(conversionJson);
        return conversions;
    }

    private String loadConversionJson() throws IOException
    {
        final InputStream inputStream = streamProvider.getInputStreamFor(CONVERSION_CONFIG_FILE_NAME);
        final String json = IOUtils.toString(inputStream);
        inputStream.close();
        return json;
    }
}
