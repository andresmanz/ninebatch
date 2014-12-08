package org.cethos.tools.ninebatch.tasks;

public class ConversionFailureException extends RuntimeException
{
    public ConversionFailureException(Throwable throwable)
    {
        super(throwable);
    }
}
