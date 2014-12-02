package org.cethos.tools.ninepatchconvert.conversion.provider;

import org.apache.commons.cli.ParseException;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.cethos.tools.ninepatchconvert.conversion.BatchArgumentParser;
import org.cethos.tools.ninepatchconvert.conversion.SingleArgumentParser;
import org.cethos.tools.ninepatchconvert.conversion.SingleConversionConfig;

import java.io.IOException;

public class ConversionProviderFactory
{
    public ConversionProvider createFrom(final String[] args) throws ParseException, IOException
    {
        ConversionProvider provider;

        if(hasSingleConversionOption(args))
        {
            provider = createSingleConversionNinePatchConfigProvider(args);
        }
        else
        {
            provider = createBatchConfigNinePatchConfigProvider(args);
        }

        return provider;
    }

    private ConversionProvider createSingleConversionNinePatchConfigProvider(final String[] args) throws ParseException
    {
        final SingleArgumentParser argumentParser = new SingleArgumentParser();
        final SingleConversionConfig config = argumentParser.createConfigFromArguments(args);
        return new SingleConversionProvider(config);
    }

    private ConversionProvider createBatchConfigNinePatchConfigProvider(final String[] args) throws ParseException
    {
        final BatchArgumentParser argumentParser = new BatchArgumentParser();
        final BatchConfig batchConfig = argumentParser.createConfigFromArguments(args);
        return new BatchConfigConversionProvider(batchConfig);
    }

    private static boolean hasSingleConversionOption(final String[] args)
    {
        boolean hasOption = false;
        for(int i = 0; i < args.length; ++i)
        {
            if(args[i].equals("-s"))
            {
                hasOption = true;
            }
        }

        return hasOption;
    }
}
