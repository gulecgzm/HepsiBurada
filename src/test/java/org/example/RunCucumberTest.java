package org.example;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( plugin = {"pretty", "json:target/cucumber.json" , "html:target/cucumber-reports.html" },
        features = "C:\\Users\\gizem.gulec\\HepsiBurada\\src\\test\\resources\\org\\example"
        // dryRun = true,
        //tags = "@AddProductToBasketWithoutLogin"
        //monochrome = true
)
public class RunCucumberTest{

}
