package org.cethos.tools.ninebatch.conversion;

public class ConversionFailureException extends RuntimeException
{
    public ConversionFailureException(Throwable throwable)
    {
        super(throwable);
    }
}
