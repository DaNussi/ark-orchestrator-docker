package net.nussi.ark.orchestrator.exception;

public class ScriptException extends Exception {
    int exitCode;

    public ScriptException(String message, int exitCode) {
        super(message);
        this.exitCode = exitCode;
    }

    public ScriptException(String message) {
        super(message);
        this.exitCode = 1;
    }

    public int getExitCode() {
        return exitCode;
    }
}
