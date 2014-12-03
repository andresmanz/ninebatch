package org.cethos.tools.ninebatch.tests.batch;

import org.cethos.tools.ninebatch.conversion.streamprovider.StreamProvider;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourceStreamProvider implements StreamProvider
{
    private int totalOutputStreamAccessCount;
    private int totalInputStreamAccessCount;

    @Override
    public OutputStream getOutputStreamFor(String fileName) throws FileNotFoundException
    {
        ++totalOutputStreamAccessCount;
        return new ByteArrayOutputStream();
    }

    @Override
    public InputStream getInputStreamFor(String fileName) throws FileNotFoundException
    {
        ++totalInputStreamAccessCount;
        return getClass().getResourceAsStream(fileName);
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
