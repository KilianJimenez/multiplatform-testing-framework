package com.example.core.hooks;

import com.example.core.Framework;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetupHooks {

    public static final Logger logger = LoggerFactory.getLogger(SetupHooks.class.getName());

    public static final String ANSI_MAGENTA_BOLD = "\u001b[35;1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static String sessionName = "";

    public SetupHooks() {
        Configurator.reconfigure();
    }

    @Before(order = 0)
    public void initDriver(Scenario scenario) {

        logger.info("Starting Driver");
        String rawFeatureName = scenario.getId();
        sessionName = scenario.getName();
        rawFeatureName = rawFeatureName.replace("%5B", "[");
        rawFeatureName = rawFeatureName.replace("%5D", "]");
        Pattern pattern = Pattern.compile("(\\[.*):");
        Matcher newRawFeatureName = pattern.matcher(rawFeatureName);
        //Set feature name
        if (newRawFeatureName.find()) {
            logger.info(ANSI_MAGENTA_BOLD + "Running : Feature  -> " + newRawFeatureName.group(1) + ANSI_RESET);
        } else {
            logger.info(ANSI_MAGENTA_BOLD + "Running : Feature  -> " + rawFeatureName.substring(28) + ANSI_RESET);
        }
        Framework.init();
    }

    @Before(order = 1)
    public void logBeforeTestRun(Scenario scenario) {
        List<String> scenarioTags = (List<String>) scenario.getSourceTagNames();
        if (scenarioTags.size() > 0) {
            String message = "Executing scenarios with tag(s):";
            for (String scenarioTag : scenarioTags) {
                message += " " + scenarioTag;
            }
            logger.info(message);
        }
    }

    @After
    public void quitDriver() {
        logger.info("[Driver] Quit driver Appium");
        Framework.customDriver.quit();
    }
}
