package definitions;


import static constans.Constant.URL_CONSORCIO;
import static org.junit.Assert.assertEquals;
import baseConfig.DriverContext;
import io.cucumber.java.en.Given;
import pages.ConsorcioPage;
import util.MetodosGenericos;

public class BackgroundConsorcio{
	
	ConsorcioPage consorcioPage = new ConsorcioPage();
	
	@Given("ingreso a la aplicacion web y navego a la url de consorcio")
	public void ingreso_a_la_aplicacion_web_y_navego_a_la_url_de_consorcio() {
	    MetodosGenericos.esperar(2);
		String url = DriverContext.getDriver().getCurrentUrl();
	    assertEquals(URL_CONSORCIO, url);
		
	}
}
