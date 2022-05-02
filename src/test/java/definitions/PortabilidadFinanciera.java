package definitions;



import static org.junit.Assert.assertEquals;
import baseConfig.DriverContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ConsorcioPage;
import util.MetodosGenericos;


public class PortabilidadFinanciera{
	
	ConsorcioPage consorcioPage = new ConsorcioPage();
	
	@When("ingreso a portate o hazte cliente")
	public void ingreso_a_portate_o_hazte_cliente() throws Throwable {
		consorcioPage.portateoHazteCliente();
		
	}
	
	@And("se selecciona portabilidad financiera")
	public void se_selecciona_portabilidad_financiera() throws Throwable {
		consorcioPage.portaCliente();
	}
	
	@Then("se visualiza el home de portabilidad financiera")
	public void se_visualiza_el_home_de_portabilidad_financiera()throws Throwable {
	    MetodosGenericos.esperar(3);
		String url = DriverContext.getDriver().getCurrentUrl();
	    assertEquals("https://portabilidad.consorcio.cl/#/", url);
	}
	
}
