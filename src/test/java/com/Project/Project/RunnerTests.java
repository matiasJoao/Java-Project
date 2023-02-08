package com.Project.Project;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"json:target/reports/CucumberReport.json", "pretty"},
        snippets = CucumberOptions.SnippetType.UNDERSCORE,
        glue = "com.Project.Project.steps")
public class RunnerTests {

}
