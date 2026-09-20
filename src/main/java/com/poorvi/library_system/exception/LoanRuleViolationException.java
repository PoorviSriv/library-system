package com.poorvi.library_system.exception;

public class LoanRuleViolationException extends RuntimeException
{
    public LoanRuleViolationException(String message)
    {
        super(message);
    }
}
