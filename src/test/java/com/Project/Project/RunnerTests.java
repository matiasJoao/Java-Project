package com.Project.Project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-reports/html/cucumber-html.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false,
        glue = "com.Project.Project",
        tags = "@PostControllerTest")
public class RunnerTests {


}
