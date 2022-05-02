package baseConfig;




import org.openqa.selenium.WebDriver;

import constans.Navegador;


public class DriverContext {
	
  private static DriverManager driverManager = new DriverManager();
  private static Navegador tipoNavegador;
  private static String ambienteURL = "";
  
  public static String getAmbienteURL() {
    return ambienteURL;
  }
  
  
  public static void setAmbienteURL(String ambienteURL) {
	    DriverContext.ambienteURL = ambienteURL;
 }
  
  public static String getTipoNavegador() {
	    return tipoNavegador.toString();
 }

  public static void setTipoNavegador(Navegador tipoNavegador) {
	    DriverContext.tipoNavegador = tipoNavegador;
  }
  
  public static void setUp(Navegador nav, String ambURL) {
	    setTipoNavegador(nav);
	    setAmbienteURL(ambURL);
	    System.out.println("Driver Context " + "Navegador: " + nav + " /Url: " + ambURL);
	    driverManager.resolveDriver(nav, ambURL);
  }
  
  public static WebDriver getDriver() {
    return driverManager.getDriver();
  }
  
  public static void quitDriver() {
	  if (driverManager != null) {
		  driverManager.getDriver().quit();
      }
  
  }
  
}
