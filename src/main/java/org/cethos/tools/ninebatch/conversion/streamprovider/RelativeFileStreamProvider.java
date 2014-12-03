package org.cethos.tools.ninebatch.conversion.streamprovider;

import org.cethos.tools.ninebatch.conversion.batch.BatchConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RelativeFileStreamProvider implements StreamProvider
{
    private final BatchConfig batchConfig;

    public RelativeFileStreamProvider(final BatchConfig batchConfig)
    {
        this.batchConfig = batchConfig;
    }

    @Override
    public OutputStream getOutputStreamFor(final String fileName) throws FileNotFoundException
    {
        final File file = new File(batchConfig.getInputDirPath(), fileName);
        return new FileOutputStream(file);
    }

    @Override
    public InputStream getInputStreamFor(final String fileName) throws FileNotFoundException
    {
        final File file = new File(batchConfig.getOutputDirPath(), fileName);
        return new FileInputStream(file);
    }
}
