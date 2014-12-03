package org.cethos.tools.ninebatch.conversion.streamprovider;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamProvider
{
    public OutputStream getOutputStreamFor(final String fileName) throws FileNotFoundException;
    public InputStream getInputStreamFor(final String fileName) throws FileNotFoundException;
}
