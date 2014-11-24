package org.cethos.ninepatch;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class NinePatchConvert
{
    public static void main(final String[] args)
    {
        final ArgumentParser argumentParser = new ArgumentParser();

        try
        {
            final BatchConfig config = argumentParser.createConfigFromArguments(args);
            final List<NinePatchConfig> ninePatchConfigs = loadNinePatchConfigsFrom(config.getInputDirPath());
            ConversionBatchProcessing.convert(config, ninePatchConfigs);
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

    private static List<NinePatchConfig> loadNinePatchConfigsFrom(final String inputDirectory) throws IOException
    {
        List<NinePatchConfig> ninePatchConfigs = Collections.emptyList();

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
