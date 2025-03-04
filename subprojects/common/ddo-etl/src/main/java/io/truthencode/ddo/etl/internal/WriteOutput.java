package io.truthencode.ddo.etl.internal;

public class WriteOutput {
    private String output;
    private Boolean valid;
    private ErrorObject errorObject;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }

    public void setErrorObject(ErrorObject errorObject) {
        this.errorObject = errorObject;
    }
}
