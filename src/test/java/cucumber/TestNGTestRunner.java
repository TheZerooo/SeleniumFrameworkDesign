package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="rahulshettyacademy.stepDefinations",
monochrome=true, tags = "@Regression",plugin= {"html:target/cucumber.html"}) // run only one testcase
//monochrome=true,plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
