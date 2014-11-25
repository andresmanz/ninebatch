package org.cethos.ninepatch;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NinePatchConvert
{
    public static void main(final String[] args)
    {
        final ArgumentParser argumentParser = new ArgumentParser();

        try
        {
            final BatchConfig config = argumentParser.createConfigFromArguments(args);
            final Map<String, NinePatchConfig> ninePatchConfigs = loadNinePatchConfigsFrom(config.getInputDirPath());
            final ConversionBatch batch = new ConversionBatch(config);
            batch.process(ninePatchConfigs);
        }
        catch(final ParseException e)
        {
            System.err.println(e.getMessage());
            argumentParser.printHelp();
        }
        catch(final IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static Map<String, NinePatchConfig> loadNinePatchConfigsFrom(final String inputDirectory) throws IOException
    {
        Map<String, NinePatchConfig> ninePatchConfigs = Collections.emptyMap();

        final FileInputStream inputStream = new FileInputStream(inputDirectory + "/ninepatches.json");
        try
        {
            final String json = IOUtils.toString(inputStream);
            ninePatchConfigs = NinePatchConfigParsing.getImageConfigsFromJson(json);
        }
        finally
        {
            inputStream.close();
        }

        return ninePatchConfigs;
    }
}
