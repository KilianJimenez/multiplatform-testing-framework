package com.example.core.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example",
        plugin = {
                "pretty",
                "html:target/cucumber_html.html",
                "json:target/cucumber_json.json",
                "junit:target/junit.xml"
        }
)
public class CucumberRunner {

}
