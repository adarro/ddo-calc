package io.truthencode.ddo.etl.internal;

// AI generated as a placeholder
public class ErrorObject {

    private String errorCode;
    private final Exception ex;


    public ErrorObject(Exception e, String errorCode) {
        this.ex = e;
        this.errorCode = errorCode;
    }

    public ErrorObject(Exception e) {
        this(e,"UNKNOWN");
    }

    public String getMessage() {
        return ex.getMessage();
    }


    public StackTraceElement[] getStackTrace() {
        return ex.getStackTrace();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
