package net.nussi.ark.orchestrator;

import net.nussi.ark.orchestrator.exception.EnvironmentVariableException;
import net.nussi.ark.orchestrator.exception.ScriptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static final Runtime runtime = Runtime.getRuntime();

    public static final String[] RequiredEnvironmentVariables = new String[] {
            "ARK_SERVER_VOLUME", "STEAM_USER", "ARK_TOOLS_DIR"
    };

    public static void main(String[] args) {
        try {
            script();
        } catch (Exception e) {
            exitWithException(e);
        }
    }

    public static void script() throws Exception {

        System.out.print("Hello World");
        logger.info("Checking environment variables");
        checkEnvironmentVariables();


    }

    public static void exitWithException(Exception exception) {
        if(exception instanceof ScriptException scriptException) {
            logger.error(scriptException.getMessage());
            logger.error("Script failed with exit code: {}", scriptException.getExitCode());
            runtime.exit(scriptException.getExitCode());
        } else {
            logger.error(exception.getMessage());
            logger.error("Script failed with exit code: 1");
            runtime.exit(1);
        }
    }

    /**
     * Check if all required environment variables are set
     * @throws EnvironmentVariableException if any required environment variable is missing
     */
    public static void checkEnvironmentVariables() throws EnvironmentVariableException {
        Map<String, String> environmentVariables = System.getenv();
        Set<String> environmentVariableKeys = environmentVariables.keySet();
        Set<String> missionEnvironmentVariables = new HashSet<>();

        for (String variableToCheck : RequiredEnvironmentVariables) {
            if(!environmentVariableKeys.contains(variableToCheck))
                missionEnvironmentVariables.add(variableToCheck);
        }

        if (!missionEnvironmentVariables.isEmpty()) {
            throw new EnvironmentVariableException(missionEnvironmentVariables.toArray(new String[0]));
        }
    }

}