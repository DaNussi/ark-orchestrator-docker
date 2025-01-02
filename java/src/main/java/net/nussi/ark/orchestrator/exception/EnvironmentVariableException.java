package net.nussi.ark.orchestrator.exception;

import java.util.Arrays;

public class EnvironmentVariableException extends ScriptException {

    public EnvironmentVariableException(String[] missingEnvironmentVariables) {
        super("Missing environment variables: " + Arrays.toString(missingEnvironmentVariables));
    }
}
