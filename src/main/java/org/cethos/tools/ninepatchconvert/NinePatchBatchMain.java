package org.cethos.tools.ninepatchconvert;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninepatchconvert.batch.BatchArgumentParser;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.cethos.tools.ninepatchconvert.batch.ConversionBatch;
import org.cethos.tools.ninepatchconvert.batch.NinePatchConfigParsing;
import org.cethos.tools.ninepatchconvert.creation.FileImageInputOutput;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class NinePatchBatchMain
{
    private static final String NINEPATCHES_CONFIG_FILE_NAME = "ninepatches.json";

    public static void main(final String[] args)
    {
        final BatchArgumentParser argumentParser = new BatchArgumentParser();

        try
        {
            final BatchConfig batchConfig = argumentParser.createConfigFromArguments(args);
            final Map<String, NinePatchConfig> ninePatchConfigs = loadNinePatchConfigs(batchConfig);
            final ConversionBatch batch = new ConversionBatch(batchConfig, new FileImageInputOutput());
            batch.process(ninePatchConfigs);
        }
        catch(final ParseException exception)
        {
            argumentParser.printHelp();
        }
        catch(final Exception exception)
        {
            System.err.println(exception.getMessage());
        }
    }

    private static Map<String, NinePatchConfig> loadNinePatchConfigs(final BatchConfig batchConfig) throws IOException
    {
        final String configJson = loadConfigJsonFrom(batchConfig.getInputDirPath());
        final Map<String, NinePatchConfig> ninePatchConfigs = NinePatchConfigParsing.parse(configJson);
        return ninePatchConfigs;
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
