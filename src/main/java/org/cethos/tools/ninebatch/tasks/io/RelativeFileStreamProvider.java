package org.cethos.tools.ninebatch.tasks.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class RelativeFileStreamProvider implements StreamProvider
{
    private final String baseInputPath;
    private final String baseOutputPath;

    public RelativeFileStreamProvider(final String baseInputPath, final String baseOutputPath)
    {
        this.baseInputPath = baseInputPath;
        this.baseOutputPath = baseOutputPath;
    }

    @Override
    public OutputStream getOutputStreamFor(final String fileName) throws FileNotFoundException
    {
        final File file = new File(baseOutputPath, fileName);
        return new FileOutputStream(file);
    }

    @Override
    public InputStream getInputStreamFor(final String fileName) throws FileNotFoundException
    {
        final File file = new File(baseInputPath, fileName);
        return new FileInputStream(file);
    }
}
