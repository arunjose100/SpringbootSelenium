package com.app.spring.springselenium.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:features",
        glue = "com.app.spring.springselenium.steps",
        tags =  "@singlenews",
        plugin = {
                "json:target/cucumber/cucumber2.json",
                "pretty",
                "html:target/site/cucumber-pretty"
        },
        monochrome = true
)
public class SinglenewsRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
