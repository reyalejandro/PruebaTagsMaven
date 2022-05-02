package definitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ConsorcioPage;


public class ConsorcioLogin{
	
	ConsorcioPage consorcioPage = new ConsorcioPage();
	
	@When("ingreso a tu cuenta")
	public void ingreso_a_tu_cuenta() throws Throwable {
		consorcioPage.consorcioHom();
		
	}
	
	@And("banca y seguros")
	public void banca_y_seguros() throws Throwable {
		consorcioPage.consorcioBancaSeguros();
	}
	
	@Then("se visualizan los campos del login {string} y {string}")
	public void se_visualizan_los_campos_del_login(String rut,String clave)throws Throwable {
		consorcioPage.validarUserPass();
	}
	
}
