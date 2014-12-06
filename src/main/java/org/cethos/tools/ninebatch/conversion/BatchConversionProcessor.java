package org.cethos.tools.ninebatch.conversion;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.conversion.batch.ConversionBatch;
import org.cethos.tools.ninebatch.conversion.batch.ConversionParsing;
import org.cethos.tools.ninebatch.conversion.io.StreamProvider;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public class BatchConversionProcessor implements ConversionProcessor
{
    private static final String CONVERSION_CONFIG_FILE_NAME = "ninepatches.json";

    private final StreamProvider streamProvider;
    private boolean isDeletingImageSourcesEnabled;

    public BatchConversionProcessor(final StreamProvider streamProvider)
    {
        this.streamProvider = streamProvider;
    }

    public void setDeletingImageSourcesEnabled(final boolean isEnabled)
    {
        this.isDeletingImageSourcesEnabled = isEnabled;
    }

    public void loadAndProcessConversions()
    {
        final Map<String, NinePatchConfig> conversions = loadConversions();
        final ConversionBatch conversionBatch = new ConversionBatch(streamProvider);
        conversionBatch.process(conversions);

        if(isDeletingImageSourcesEnabled)
        {
            deleteOriginalImageSources(conversions.keySet());
        }
    }

    private Map<String, NinePatchConfig> loadConversions()
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

    private void deleteOriginalImageSources(final Set<String> fileNames)
    {
        for(final String fileName : fileNames)
        {
            streamProvider.deleteImageSource(fileName);
        }
    }
}
