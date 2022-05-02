package runners;

import org.junit.runner.RunWith;

import definitions.Hooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(glue = { "definitions" },
                 features = "src/test/resources/features", 
                 plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
                		 ,"json:results/cucumber.json"
                		 ,"junit:results/cucumber.xml"},
                 tags = "@regression",
                 monochrome = true)

public class Regresion extends Hooks{
	
	
}
