package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourceStreamProvider implements StreamProvider
{
    private final String basePath;
    private int totalOutputStreamAccessCount;
    private int totalInputStreamAccessCount;

    public ResourceStreamProvider(final String basePath)
    {
        this.basePath = basePath;
    }

    @Override
    public OutputStream getOutputStreamFor(final String fileName) throws FileNotFoundException
    {
        ++totalOutputStreamAccessCount;
        return new ByteArrayOutputStream();
    }

    @Override
    public InputStream getInputStreamFor(final String fileName) throws FileNotFoundException
    {
        ++totalInputStreamAccessCount;

        final InputStream stream = getClass().getResourceAsStream(basePath + "/" + fileName);
        if(stream == null)
        {
            throw new FileNotFoundException("File not found: " + fileName);
        }
        else
        {
            return stream;
        }
    }

    public int getTotalOutputStreamAccessCount()
    {
        return totalOutputStreamAccessCount;
    }

    public int getTotalInputStreamAccessCount()
    {
        return totalInputStreamAccessCount;
    }
}
