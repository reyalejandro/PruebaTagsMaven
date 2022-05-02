package pages;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseConfig.DriverContext;
import util.MetodosGenericos;
public class ConsorcioPage {

    private WebDriver driver;

    public ConsorcioPage() {
    	this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);

    }
    
	@FindBy(xpath = "//button[@class='btn-your-account d-flex justify-content-center']")
	WebElement btnIngresaTuCuenta;
	
	@FindBy(xpath = "//div[@class='container-fluid container-max py-2 d-none d-769-block container-pad']//div[@class='login-account-modal']//button[@id='header_personas_banco_seguros']")
	WebElement bancaSeguros;

	@FindBy(xpath = "//input[@name='username']")
	WebElement user;

	@FindBy(xpath = "//input[@name='pass']")
	WebElement pass;
	
	@FindBy(xpath = "//button[@id='hi_header_login_a']")
	WebElement portateHazteCliente;
	
	@FindBy(xpath = "//button[@id='header_portabilidad_financiera']")
	WebElement portabilidadFinanciera;
	
	
	@FindBy(xpath = "//input[@name='usuario']")
	WebElement userr;
	
	
	@FindBy(xpath = "//input[@name='clave']")
	WebElement passs;
	
	@FindBy(xpath = "//input[@class='boton2']")
	WebElement btnEntrar;
	
	
	

	public void consorcioHom() throws Exception {
		MetodosGenericos.waitExplicit(btnIngresaTuCuenta,5);
		btnIngresaTuCuenta.click();

	}
	
	public void consorcioBancaSeguros() throws Exception {
		MetodosGenericos.waitExplicit(bancaSeguros,5);
		bancaSeguros.click();

	}
	
	
	
	public void validarUserPass() throws Exception {
	
		boolean estadoUser= MetodosGenericos.visualizarObjeto(user, 5);
		if(!estadoUser) {
			fail("no se visualiza el input user");
		}
		
		boolean estadoPass= MetodosGenericos.visualizarObjeto(pass, 5);
		if(!estadoPass) {
			fail("no se visualiza el input pass");
		}
		
	}

	public void portateoHazteCliente() throws Exception {
		MetodosGenericos.waitExplicit(portateHazteCliente,5);
		portateHazteCliente.click();

	}
	

	public void portaCliente() throws Exception {
		MetodosGenericos.waitExplicit(portabilidadFinanciera,5);
		portabilidadFinanciera.click();

	}
	
	
}
