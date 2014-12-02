package org.cethos.tools.ninepatchconvert.imageprocessor;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.cethos.tools.ninepatchconvert.batch.BatchConfig;
import org.cethos.tools.ninepatchconvert.batch.ConversionBatch;
import org.cethos.tools.ninepatchconvert.batch.NinePatchConfigParsing;
import org.cethos.tools.ninepatchconvert.creation.FileNinePatchIO;
import org.cethos.tools.ninepatchconvert.creation.NinePatchConfig;
import org.cethos.tools.ninepatchconvert.creation.NinePatchIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class BatchImageProcessor implements ImageProcessor
{
    private static final String NINEPATCHES_CONFIG_FILE_NAME = "ninepatches.json";

    private final BatchConfig batchConfig;
    private final NinePatchIO ninePatchIO;

    public BatchImageProcessor(final BatchConfig batchConfig) throws ParseException
    {
        this.batchConfig = batchConfig;
        this.ninePatchIO = createNinePatchInputOutputFor(batchConfig);
    }

    private static NinePatchIO createNinePatchInputOutputFor(final BatchConfig batchConfig)
    {
        final String inputDirPath = batchConfig.getInputDirPath();
        final String outputDirPath = batchConfig.getOutputDirPath();
        return new FileNinePatchIO(inputDirPath, outputDirPath);
    }

    @Override
    public void process() throws IOException
    {
        final Map<String, NinePatchConfig> ninePatchConfigs = loadNinePatchConfigs(batchConfig);
        final ConversionBatch conversionBatch = new ConversionBatch(ninePatchIO);
        conversionBatch.process(ninePatchConfigs);
    }

    private static Map<String, NinePatchConfig> loadNinePatchConfigs(final BatchConfig batchConfig) throws IOException
    {
        final String configJson = loadConfigJsonFrom(batchConfig.getInputDirPath());
        final Map<String, NinePatchConfig> configs = NinePatchConfigParsing.parse(configJson);
        return configs;
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
