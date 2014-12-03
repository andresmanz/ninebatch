package org.cethos.tools.ninebatch.conversion.provider;

import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninebatch.batch.BatchConfig;
import org.cethos.tools.ninebatch.conversion.ConversionParsing;
import org.cethos.tools.ninebatch.creation.NinePatchConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class BatchConfigConversionProvider implements ConversionProvider
{
    private static final String NINEPATCHES_CONFIG_FILE_NAME = "ninepatches.json";

    private final BatchConfig batchConfig;

    public BatchConfigConversionProvider(final BatchConfig batchConfig)
    {
        this.batchConfig = batchConfig;
    }

    @Override
    public Map<String, NinePatchConfig> createConfigs() throws IOException
    {
        final String configJson = loadConfigJsonFrom(batchConfig.getInputDirPath());
        final Map<String, NinePatchConfig> ninePatchConfigs = ConversionParsing.parse(configJson);
        addBasePathToConfigs(ninePatchConfigs);
        return ninePatchConfigs;
    }

    // TODO that shouldn't be necessary
    private void addBasePathToConfigs(final Map<String, NinePatchConfig> configs)
    {
        for(final Map.Entry<String, NinePatchConfig> config : configs.entrySet())
        {
            final Path path = Paths.get(batchConfig.getInputDirPath(), config.getKey());
            final NinePatchConfig tmpConfig = configs.remove(config.getKey());
            configs.put(path.toString(), tmpConfig);
        }
    }

    private static String loadConfigJsonFrom(final String inputDirectory) throws IOException
    {
        final File configFile = new File(inputDirectory, NINEPATCHES_CONFIG_FILE_NAME);
        final FileInputStream inputStream = new FileInputStream(configFile);
        final String json = IOUtils.toString(inputStream);
        inputStream.close();
        return json;
    }
}
