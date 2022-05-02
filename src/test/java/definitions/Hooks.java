package definitions;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import static constans.Constant.URL_CONSORCIO;
import baseConfig.DriverContext;
import constans.Navegador;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks{
	public WebDriver driver;
	private Scenario scenario;
	private static String takeEvidence;  
	
	static {
		takeEvidence = System.getProperty("evidence", "fullEvidence");
		
	}
	@Before
    public void setUp(Scenario scenario)throws Throwable {
	   this.scenario = scenario;
       DriverContext.setUp(Navegador.Chrome, URL_CONSORCIO);
    }

    
	@After
	public void tearDown(Scenario scenario){
		DriverContext.quitDriver();
	
	}

	@AfterStep
	public void capturarEvidencia() throws Exception{
		if (this.scenario.isFailed()) {
			generateEvidence("[FAIL] Scenario ScreenShots");
		} else if (Hooks.takeEvidence.equalsIgnoreCase("fullEvidence")) {
			generateEvidence("[SUCCESS] Step ScreenShots");
		}
	}
	
	public void generateEvidence(String imageRefName) {
		byte[] screenShot = ((TakesScreenshot) DriverContext.getDriver()).getScreenshotAs(OutputType.BYTES);
		this.scenario.attach(screenShot, "image/png", imageRefName);
	}


    
}