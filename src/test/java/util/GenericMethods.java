package util;


import java.awt.AWTException;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseConfig.DriverContext;


public final class GenericMethods{
    private WebDriver driver;

    public GenericMethods() {
        this.driver = DriverContext.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    private static final Log log = LogFactory.getLog(GenericMethods.class);

    public static void waitTime(WebElement object) throws InterruptedException {
        int i = 0;
        do {
            Thread.sleep(i + 1);
        } while (object.isDisplayed() == false);

    }

    public static boolean ValidateListClickElement(WebElement contLista, String opcionList) {
        Boolean resp = true;
        int numOption = 0;
        List<WebElement> listaDinamica = contLista.findElements(By.className("autocomplete-subitems"));
        if (listaDinamica.size() > 0) {
            List<String> dateListString = new ArrayList<String>();
            for (WebElement nombre : listaDinamica) {
                dateListString.add(nombre.getText());
            }
            for (int i = 0; i < dateListString.size(); i++) {
                if (dateListString.get(i).equalsIgnoreCase(opcionList)) {
                    numOption = i;
                    break;
                }
            }
            listaDinamica = contLista.findElements(By.className("autocomplete-subitems"));
            listaDinamica.get(numOption).click();
        } else {
            resp = false;
        }

        return resp;
    }

    public static boolean ValidateListCheckElement(WebElement contLista, String opcionList) {
        Boolean resp = true;
        int numOption = 0;
        List<WebElement> listaDinamica = contLista.findElements(By.className("autocomplete-subitems"));
        if (listaDinamica.size() > 0) {
            List<String> dateListString = new ArrayList<String>();
            for (WebElement nombre : listaDinamica) {
                dateListString.add(nombre.getText());
            }
            for (int i = 0; i < dateListString.size(); i++) {
                if (dateListString.get(i).equalsIgnoreCase(opcionList)) {
                    numOption = i;
                    break;
                }
            }
            listaDinamica = contLista.findElements(By.className("autocomplete-subitems"));
            listaDinamica.get(numOption).click();
        } else {
            resp = false;
        }

        return resp;
    }

    public static WebElement implicityWait(WebDriver driver, int timeoutInSeconds, By element) throws Exception {
        WebElement elemento = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            log.info(e);
            throw e;
        }
        return elemento;
    }

    public static WebElement waitForClickeable(WebDriver driver, WebElement element, int time)
            throws Exception {
        WebElement elemento = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            elemento = wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            log.info(e);
            throw e;
        }
        return elemento;
    }

    public static Boolean existElement(WebDriver driver, By element) throws Exception {
        boolean isPresent = false;
        try {
            if (driver.findElement(element) != null) {
                isPresent = true;
            }
        } catch (Exception e) { //
            isPresent = false;
        }
        return isPresent;
    }

    public static WebElement findElementReturnWebElement(WebDriver driver, By element) throws InterruptedException {
        WebElement webElement = null;
        for (int i = 0; i < 4; i++) {
            try {
                if (driver.findElement(element) != null) {
                    webElement = driver.findElement(element);
                }
            } catch (Exception e) { //
                System.out.println(e.getLocalizedMessage());
                Thread.sleep(1000);
            }
        }
        return webElement;
    }

    public static void ingresoCaja(String caja, String grupoImpres) throws InterruptedException {
        // try {
        // waitLoadingPage();
        // if (isElementPresent( pageModel.getMsjeCajaAbierta())) {
        //  pageModel.getBtnMsjeCajaAbContinuar().click();
        // } else {
        //  pageModel.getComboCajasDisp().sendKeys(caja);
        //  pageModel.getComboCajasDisp().sendKeys(Keys.ENTER);
        //  pageModel.getComboGrupoImpres().sendKeys(grupoImpres);
        //  pageModel.getComboGrupoImpres().sendKeys(Keys.ENTER);
        //  pageModel.getBtnAbrirCaja().click();
        // }
        // } catch (Exception e) {
        // assertTrue("Error", false);
        // }

    }

    public static void scrollClickElement1(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void DeleteFileIfExist(File file) {
        if (!file.exists()) {
            file.delete();
        }
    }

    /**
     * Metodo que se encarga de esperar X cantidad de segundos durante la ejecucion de la prueba
     * utilizando el driver
     *
     * @param driver
     * @param segundos
     */
    public static void esperarSegundos(WebDriver driver, int segundos) {
        synchronized (driver) {
            try {
                driver.wait(segundos * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Devolver path con rutas ya configuradas para Chrome
     */
    public static String getProperty() {
        String dirDriver = System.getProperty("user.dir");
        String path = dirDriver + "\\WebDriver\\Chrome\\Windows\\chromedriver.exe";
        return path;
    }

    /**
     * Devolver path con rutas ya configuradas para IExplorer
     */
    public static String getPropertyIE() {
        String dirDriver = System.getProperty("user.dir");
        String path = dirDriver + "\\WebDriver\\IExplorer\\Windows\\64\\IEDriverServer.exe";
        return path;
    }

    /**
     * Fecha actual
     */
    public static String now() {
        Calendar ahora = Calendar.getInstance();
        Integer n = new Integer((ahora.get(Calendar.YEAR) * 10000)
                + ((ahora.get(Calendar.MONTH) + 1) * 100) + (ahora.get(Calendar.DAY_OF_MONTH)));
        return n.toString();
    }

    /**
     * Tomar Screenshoot
     *
     * @param driver
     * @param nombre El cual se dara a la captura
     */
    public static void screenShot(WebDriver driver, String nombre) {
        try {
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(
                    System.getProperty("user.dir") + "\\Screenshots\\" + nombre + "_" + now() + ".jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambiar el foco de ventana web
     *
     * @param driver
     * @param cambiarSoloUna En caso de haber mas de dos ventana y solo querer cambiar a la siguiente,
     *                       true
     */
    public static void cambiarVentanaWeb(WebDriver driver, boolean cambiarSoloUna) {
        String MainWindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                if (cambiarSoloUna)
                    break;
            }
        }
    }

    /**
     * Cerrar ventana web, solo cierra ventana sin detener driver
     *
     * @param driver WebDriver
     */
    public static void cerrarVentanaWeb(WebDriver driver) {
        String MainWindow = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.close();
                driver.switchTo().window(ChildWindow);
            }
        }
    }

    /**
     * Sroll hasta final de pagina
     *
     * @param driver WebDriver
     * @return void
     */
    public static void finalVentana(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
    }

    /**
     * scrollElement, realiza scroll de pagina en donde se encuentra objeto
     *
     * @param driver  WebDriver
     * @param element WebElement donde se realizara el Scroll
     * @return void
     */
    public static void scrollElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * scrollClickElement, realiza scroll y ademas click al elemento de la pagina
     *
     * @param element WebElement donde realizara Scroll y hara click
     * @return void
     */
    public static void scrollClickElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * obtenerTextoNovisible, retorna el texto de un elemento que no esta visible como por ejemplo en
     * un div donde hay lista de objetos y estos no se visualizan todos
     *
     * @param driver  WebDriver
     * @param element WebElement que se obtendra el texto
     * @return String
     */
    public static String obtenerTextoNoVisible(WebDriver driver, WebElement element) {
        String texto = "";
        try {
            texto = element.getText().toString().trim();
            texto = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText",
                    element);
        } catch (Exception e) {
            texto = "";
        }
        return texto;
    }

    /**
     * ingresarCaracteresEspecialesID, en algunos navegadores no ingresa caracteres especiales como @
     * de forma correcta, este metodo mitiga ese conflicto
     *
     * @param driver           WebDriver
     * @param element          WebElement al cual se ingresara frase o caracter especial
     * @param id               Identificador ID del elemento al que se ingresara caracter
     * @param caracterEspecial Frase o caracter especial a ingresar
     * @return void
     */
    public static void ingresarCaracteresEspecialesID(WebDriver driver, WebElement element, String id,
                                                      String caracterEspecial) {
        try {
            ((JavascriptExecutor) driver).executeScript(String.format(
                    "document.getElementById(\"" + id + "\").value=\"" + caracterEspecial + "\";", element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean unloadWebElementByClass(WebDriver driver, WebElement webElement,
                                                  int segundos) {
        Boolean elementExist = isElementPresent(webElement);
        int conTiempo = 0;
        String claseTag = "";
        if (elementExist)
            claseTag = webElement.getAttribute("class");
        while (elementExist == true && conTiempo <= segundos) {
            elementExist = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("var elemento = document.getElementsByClassName('" + claseTag
                            + "');return (elemento.length == 0)?false:true;");
            esperarSegundos(driver, 2);
            conTiempo = conTiempo + 2;
        }
        return elementExist;
    }

    /**
     * Comprobar si despliega alert y cerrar este mismo
     *
     * @param driver
     * @param aceptar Para casos de confirm se puede aceptar o continuar con operacion
     */
    public static void checkAlert(WebDriver driver, boolean aceptar) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            if (driver.switchTo().alert() != null) {
                Alert alert = driver.switchTo().alert();
                if (aceptar) // OK
                    alert.accept();
                else // Cancel
                    alert.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprobar si despliega alert, obtiene mensaje de este y luego lo cierra
     *
     * @param driver
     */
    public static String textoAlert(WebDriver driver) {
        String texto = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            if (driver.switchTo().alert() != null) {
                Alert alert = driver.switchTo().alert();
                texto = alert.getText().toString().trim();
                alert.accept();
            }
        } catch (Exception e) {
            texto = "";
            e.printStackTrace();
        }
        return texto;
    }

    /**
     * Cerrar ventana WebDriver
     *
     * @param driver
     */
    public static void cerrarVentana(WebDriver driver) {
        driver.close();
    }

    /**
     * Cerrar proceso WebDriver
     *
     * @param driver
     */
    public static void cerrarDriver(WebDriver driver) {
        driver.quit();
    }

    /**
     * Volver atras
     *
     * @param driver
     */
    public static void volver(WebDriver driver) {
        driver.navigate().back();
    }

    /**
     * Comprobar si el valor es numerico
     *
     * @param valor Parametro a comprobar si es numerico
     */
    public static boolean isNumeric(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
    }

    /**
     * <b>Nombre:</b> esperaElementoSegundos</br>
     * </br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado es encontrado.
     *
     * @param segundos (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro
     * del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static boolean esperaElementoSegundos(WebDriver driver, WebElement webElement,
                                                 int segundos) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * <b>Nombre:</b> esperaElementoSegundos</br>
     * </br>
     * <b>Description:</b> Genera una pausa explicita hasta que lista de elementos dados sean
     * encontrados.
     *
     * @param driver      Controlador WebDrive.
     * @param listElement Lista de elementos a esperar.
     * @param segundos    (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro
     * del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static boolean esperaElementosSegundos(WebDriver driver, List<WebElement> listElement,
                                                  int segundos) {
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(segundos));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(listElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * <b>Nombre:</b> notEsperaElementoSegundos</br>
     * </br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado desaparece.
     *
     * @param {@link   WebDriver} Controlador WebDrive.
     * @param {@link   WebElement} Elemento a esperar.
     * @param segundos (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro
     * del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public static boolean notEsperaElementoSegundos(WebDriver driver, WebElement webElement,
                                                    int segundos) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
        if (isElementPresent(webElement)) {
            try {
                wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * <b>Nombre:</b> esperaElementoException</br>
     * </br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado es encontrado.
     *
     * @param {@link   WebDriver} Controlador WebDrive.
     * @param {@link   WebElement} Elemento a esperar.
     * @param segundos (int) Valor de tiempo en segundos a esperar.
     * @return {@link Void} Retorna un <b>TimeoutException</b> si el elemento NO es encontrado dentro
     * del tiempo estipulado, de lo contrario solo continua con el flujo normal.
     **/
    public static void esperaElementoException(WebDriver driver, WebElement webElement,
                                               int segundos) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(segundos));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * <b>Nombre:</b> isElementPresent</br>
     * </br>
     * <b>Description:</b> Verifica la existencia de un elemento
     *
     * @param {@link WebElement} Objeto de tipo WebElement a buscar
     * @return {@link Boolean} Retorna <b>True</b> si el elemento es encontrado, de lo contrario
     * retorna <b>False</b>
     **/
    public static boolean isElementPresent(WebElement webElement) {
        boolean resp = false;
        try {
            resp = webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            resp = false;
        }
        return resp;
    }


    public static Boolean existWebElement(WebElement elementoLista) throws InterruptedException {
        boolean isPresent = false;
        try {
            if (elementoLista != null) {
                isPresent = true;
            }
        } catch (Exception e) { //
            isPresent = false;
        }
        return isPresent;
    }
    /**
     * <b>Nombre:</b> loadPage</br>
     * </br>
     * <b>Description:</b> Espera que pagina cargue por completo una vez que desaparezca el icono de
     * cargando en scotiaweb o bien se detendra de forma automatica una vez pasen 2 min en caso de no
     * respuesta
     *
     * @return void
     **/
    // public static void loadPage() {
    // long time = 0;
    // do {
    // esperarSegundos(AplicationRunning.driverWeb, 1);
    // time++;
    // } while (AplicationRunning.pageSitioPrivadoWeb.cargando() && time < 120);
    // }

    /**
     * <b>Nombre:</b> isClickable</br>
     * </br>
     * <b>Description:</b> Comprueba si elemento es posible hacerle click
     *
     * @param element WebElement a verificar
     * @return boolean
     **/
    public static boolean isClickable(WebDriver driver, Integer timeout, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String dateAdd(int intervalo, int numero, String fecha) {
        int ano = ((new Integer(fecha)).intValue() / 10000);
        int mes = (((new Integer(fecha)).intValue() - (ano * 10000)) / 100);
        int dia = (((new Integer(fecha)).intValue() - (ano * 10000) - (mes * 100)));

        Calendar f = Calendar.getInstance();
        f.set(ano, mes - 1, dia);

        f.add(intervalo, numero);

        String anoStr = String.valueOf(f.get(Calendar.YEAR));
        String mesStr = String.valueOf(f.get(Calendar.MONTH) + 1);
        String diaStr = String.valueOf(f.get(Calendar.DAY_OF_MONTH));

        anoStr = "0000" + anoStr.trim();
        mesStr = "00" + mesStr.trim();
        diaStr = "00" + diaStr.trim();

        anoStr = anoStr.substring(anoStr.length() - 4);
        mesStr = mesStr.substring(mesStr.length() - 2);
        diaStr = diaStr.substring(diaStr.length() - 2);

        String fechanueva = anoStr + mesStr + diaStr;

        return fechanueva;
    }

    public static long dateDiff(String fechaInicio, String fechaFin) {
        return dateDiff(fechaInicio, fechaFin, 0);
    }

    public static long dateDiff(String fechaInicio, String fechaFin, int intervalo) {
        Calendar inicio = Calendar.getInstance();
        Calendar fin = Calendar.getInstance();

        inicio.set(Integer.parseInt(fechaInicio.substring(0, 4)),
                Integer.parseInt(fechaInicio.substring(4, 6)) - 1,
                Integer.parseInt(fechaInicio.substring(6, 8)));
        fin.set(Integer.parseInt(fechaFin.substring(0, 4)),
                Integer.parseInt(fechaFin.substring(4, 6)) - 1, Integer.parseInt(fechaFin.substring(6, 8)));

        inicio.add(Calendar.DAY_OF_MONTH, +intervalo);// agregar intervalo a
        // fecha de inicio

        return fin.getTimeInMillis() - inicio.getTimeInMillis();
    }

    public static long diferenciaDias(String fechaInicio, String fechaFin) {
        long dias = 0;
        dias = dateDiff(fechaInicio, fechaFin) / (3600 * 24 * 1000);
        return dias;
    }

    public static String formateaFecha(String fecha) {
        try {
            String fechaFormato = fecha.substring(6, 8);
            fechaFormato += "/" + fecha.substring(4, 6);
            fechaFormato += "/" + fecha.substring(0, 4);

            return fechaFormato;
        } catch (Exception e) {
            return fecha;
        }
    }

    /**
     * Crear digito verificador mediante numeros x entregados
     *
     * @param r digitos para generar rut
     * @return String
     */
    public static String generaDvRut(String r) {
        String dv = "";
        int rut, digito, suma, resto, resultado, factor;
        // Para el manejo de entrada estándar
        rut = Integer.parseInt(r);

        // Ahora viene la parte de extraer dígito por dígito el rut
        for (factor = 2, suma = 0; rut > 0; factor++) {
            digito = rut % 10;
            rut /= 10;
            suma += digito * factor;

            if (factor >= 7)
                factor = 1; // Para volver al ciclo
        }
        // Ahora viene el algoritmo del módulo 11
        resto = suma % 11;
        resultado = 11 - resto;

        // Mostramos por pantalla.
        // Si el resultado es menor que 10, se imprime el número.
        // Si es igual a 10, entonces se imprime "K"
        // Si no, entonces es 0
        if (resultado < 10)
            dv = String.valueOf(resultado);
        else if (resultado == 10)
            dv = "K";
        else
            dv = "0";

        return dv;
    }

    public static Boolean existWebElement(WebElement elementoLista, By element)
            throws InterruptedException {
        boolean isPresent = false;
        try {
            if (elementoLista.findElement(element) != null) {
                isPresent = true;
            }
        } catch (Exception e) { //
            Thread.sleep(1000);
        }

        return isPresent;
    }

    public static int round(double d) {
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - i;
        if (result < 0.5) {
            return d < 0 ? -i : i;
        } else {
            return d < 0 ? -(i + 1) : i + 1;
        }
    }

    public static void clickelement(WebDriver driver, WebElement element) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }
    /**
     * <b>Nombre:</b> nowHoursr</br>
     * </br>
     * <b>Description:</b> Metodo que permite obtener la hora precisa
     */
    public static String nowHours() {
        Calendar ahora = Calendar.getInstance();
        Integer hora = new Integer((ahora.get(Calendar.HOUR_OF_DAY)));
        Integer minuto = new Integer((ahora.get(Calendar.MINUTE)));
        Integer segundo = new Integer((ahora.get(Calendar.SECOND)));
        return hora.toString() + minuto.toString() + segundo.toString();
    }

    /**
     * <b>Nombre:</b> waitLoadingPage</br>
     * </br>
     * <b>Description:</b> Metodo que permite esperar interacturar y esperar con el gráfico de carga
     * de la página
     */
    public static void waitLoadingPage(WebDriver driver) {
        int i = 0;
        do {
            try {
                Thread.sleep(i++);
            } catch (InterruptedException e) {
                System.err.println("Interrupción en la carga de la página");
            }
        } while (driver.findElements(By.className("la-ball-spin-clockwise")).size() > 0);
    }

    /**
     * <b>Nombre:</b> compareString</br>
     * </br>
     * <b>Description:</b> Método que permite poder comparar el valor de dos Strings
     *
     * @param {@link String} Primer parametro de tipo String
     * @param {@link String} Segundo Parametro de tipo String
     * @return {@link Boolean} Retorna <b>True</b> si son iguales <b>False</b>
     */
    public static boolean compareStrings(String value1, String value2) {
        boolean response = false;
        try {
            if (value1.equals(value2)) {
                response = true;
            } else {
                response = false;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return response;
    }

    /**
     * <b>Nombre:</b> compareListString</br>
     * </br>
     * <b>Description:</b> Método que permite poder comparar el valor de un string con otro que esta
     * dentro de una lista
     *
     * @param {@link List<WebElement> } Primer parametro de tipo List webElement
     * @param {@link String} Segundo Parametro de tipo String
     * @return {@link Boolean} Retorna <b>True</b> si son iguales <b>False</b>
     */
    public static boolean compareListString(List<WebElement> lista, String valor) {
        Boolean flag = false;
        String valordetlista;
        List<WebElement> listValor = lista;
        for (WebElement valorDetList : listValor) {
            valordetlista = valorDetList.getAttribute("innerText").trim();
            if (GenericMethods.compareStrings(valordetlista, valor.trim())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * <b>Nombre:</b> compareListString</br>
     * </br>
     * <b>Description:</b> Método que suma todos los datos de una lista
     *
     * @param {@link List<WebElement> } Primer parametro de tipo List webElement
     * @param {@link String} Segundo Parametro de tipo String
     * @return {@link Boolean} Retorna <b>True</b> si son iguales <b>False</b>
     */
    public static boolean sumarDatosLista(List<WebElement> lista, String valor) {
        try {
            int n;
            int monto;
            int montoTotal = 0;
            int montoTotal2 = 0;
            List<WebElement> listMonto = lista;
            n = listMonto.size();
            for (int i = 0; i < n; i = i + 1) {
                if (!lista.get(i).getAttribute("innerText").trim().equals("")) {
                    monto = Integer.parseInt(lista.get(i).getAttribute("innerText").replace("$", "")
                            .replace(".", "").replace("(=)", "").trim());
                    montoTotal = monto + montoTotal;
                }
            }
            montoTotal2 =
                    Integer.parseInt(valor.replace("$", "").replace(".", "").replace("(=)", "").trim());
            if (montoTotal != montoTotal2) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * <b>Nombre:</b> isElementEnabled</br>
     * </br>
     * <b>Description:</b> Verifica si elemento se encuentra habilitado
     *
     * @param {@link WebElement} Objeto de tipo WebElement
     * @return {@link Boolean} Retorna <b>True</b> si el elemento se encuentra habilitado, de lo
     * contrario retorna <b>False</b>
     **/
    public static boolean isElementEnabled(WebElement webElement) {
        boolean resp = false;
        try {
            resp = webElement.isEnabled();
        } catch (Exception e) {

            resp = false;
        }
        return resp;
    }

    /**
     * <b>Nombre:</b> isElementEnabled</br>
     * </br>
     * <b>Description:</b> Verifica si elemento se encuentra habilitado
     *
     * @param {@link WebElement} Objeto de tipo WebElement
     * @return {@link Boolean} Retorna <b>True</b> si el elemento se encuentra habilitado, de lo
     * contrario retorna <b>False</b>
     **/
    public static boolean isElementListEnabled(List<WebElement> webElement) {

        int n;
        boolean resp = false;
        n = webElement.size();

        for (int i = 0; i < n; i++) {
            try {
                resp = webElement.get(i).isEnabled();
            } catch (Exception e) {
                resp = false;
            }
        }
        return resp;
    }

    /**
     * <b>Nombre:</b> isElementEnabled</br>
     * </br>
     * <b>Description:</b> Verifica si elemento se encuentra habilitado
     *
     * @param {@link WebElement} Objeto de tipo WebElement
     * @return {@link Boolean} Retorna <b>True</b> si el elemento se encuentra habilitado, de lo
     * contrario retorna <b>False</b>
     **/
    public static void selectElementList(List<WebElement> webElement, String elemento) {

        int n;
        n = webElement.size();
        for (int i = 0; i < n; i = i + 1) {
            if (webElement.get(i).getAttribute("innerText").trim().equals(elemento)) {
                webElement.get(i).click();
            }
        }
    }

    /**
     * <b>Nombre:</b> isElementDisabled</br>
     * </br>
     * <b>Description:</b> Verifica si elemento se encuentra deshabilitado
     *
     * @param {@link WebElement} Objeto de tipo WebElement
     * @return {@link Boolean} Retorna <b>True</b> si el elemento se encuentra deshabilitado, de lo
     * contrario retorna <b>False</b>
     **/
    public static boolean isElementDisabled(WebElement webElement) {
        boolean resp = false;
        try {
            if (webElement.isEnabled()) {
                resp = false;
            } else {
                resp = true;
            }
        } catch (Exception e) {
        }
        return resp;
    }

    public static String ingresoMailNavegador(WebDriver driver, String usuario, String clave, String nombreCorreo)
            throws InterruptedException {

        //  pageModel.getBtnCerrarSesion().click();
        // Thread.sleep(5000);
        //  pageModel.getTxtUsuario().sendKeys("HAK_CCHELME");
        //  pageModel.getTxtContraseña().sendKeys("password$");
        //  pageModel.getBtnIniciarSesion().click();

        String claveSeguridad = "";
        ((JavascriptExecutor) driver).executeScript(
                "window.open('https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession','_blank');");

        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        Thread.sleep(5000);
        WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys("carla.chelme@hakalab.com");
        driver.findElement(By.id("identifierNext")).click();
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.sendKeys("martina03");
        driver.findElement(By.id("passwordNext")).click();
        GenericMethods.waitLoadingPage(driver);
        // zF es la clase para correos no leidos
        // yP es la clase para correos leidos


        // List<WebElement> correoClave =  driver
        // .findElements(By.xpath("//span[contains(@email,\"Bupa Connecting People - Segunda clave\")and
        // contains(@class,'zF')]"));
        // correoClave.get(1).click();

        Thread.sleep(2000);
        driver
                .findElements(By.xpath("//span[contains(@email,\"BCP@bupa.cl\")and contains(@class,'zF')]"))
                .get(1).click();

        // System.out.println("asunto del correo :"
        // +  driver.findElement(By.xpath("//h2[contains(@class,'hP')]")).getText());
        // System.out.println("cuerpo del correo :"
        // +  driver.findElement(By.xpath("//div[contains(@class,'a3s')]")).getText());
        // Thread.sleep(5000);
        // System.out.println("clave secreta del mail :"
        // +
        //  driver.findElement(By.xpath("//td[contains(@align,'center')]")).getAttribute("innerText"));
        Thread.sleep(3000);
        claveSeguridad = driver.findElement(By.xpath("//td[contains(@align,'center')]"))
                .getAttribute("innerText");
        driver.switchTo().window(tabs2.get(0));
        Thread.sleep(3000);
        //  pageModel.getTxtClaveDinamicaOpt().sendKeys(claveSeguridad);
        //  pageModel.getBtnValidarTokenOpt().click();
        return claveSeguridad;


    }

    public static String ingresoMail(WebDriver driver, String usuario, String clave, String nombreCorreo)
            throws InterruptedException, AWTException {

        WebDriver driverChrome;
        driverChrome = new ChromeDriver();

        String claveSeguridad = "";
        ((JavascriptExecutor) driverChrome).executeScript(
                "window.open('https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession','_blank');");

        ArrayList<String> tabs2 = new ArrayList<String>(driverChrome.getWindowHandles());

        driverChrome.switchTo().window(tabs2.get(1));
        Thread.sleep(5000);
        WebElement email_phone = driverChrome.findElement(By.xpath("//input[@id='identifierId']"));
        email_phone.sendKeys(usuario);
        driverChrome.findElement(By.id("identifierNext")).click();

        Thread.sleep(5000);

        driverChrome.findElement(By.xpath("//input[@name='password']")).sendKeys(clave);
        driverChrome.findElement(By.id("passwordNext")).click();
        waitLoadingPage(driver);
        // zF es la clase para correos no leidos
        // yP es la clase para correos leidos
        Thread.sleep(5000);
        //Thread.sleep(300000);
        //driverChrome
        //        .findElements(By.xpath("//span[contains(@email,\"admin@evergent.com\")and contains(@class,'zF')]"))
        //        .get(1).click();
        driverChrome
                .findElements(By.xpath("//span[contains(@email,'noreply@wom.cl')and contains (@class,'zF')]"))
                .get(1).click();
        Thread.sleep(2000);
        claveSeguridad = driverChrome.findElement(By.xpath("//font[@style='font-size:36px;font-weight:bold;color:#4c078c;line-height:100%']"))
                .getAttribute("innerText");

        Set<String> handlesSet = driverChrome.getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handlesSet);
        driverChrome.switchTo().window(handlesList.get(1));
        driverChrome.close();
        driverChrome.switchTo().window(handlesList.get(0));
        driverChrome.close();
        String a = driver.getWindowHandle();
        driver.switchTo().window(a);
        System.out.println("xxx:" + claveSeguridad);
        return claveSeguridad;
    }

    public static Boolean isVisibleElement(WebDriver driver, Integer timeoutInSeconds, WebElement element)
            throws Exception {
        Boolean isVisible = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(timeoutInSeconds));
            isVisible = wait.until(ExpectedConditions.visibilityOf(element)) != null ? true : false;
        } catch (Exception e) {
        }
        return isVisible;
    }


    public static void verificarElemento(WebElement element) {
        try {
            if (visualizarObjeto(element, 50)) {
                Assert.assertTrue("Se comprueba que existe Elemento " + element, true);
            } else {
                Assert.fail("No se puede verificar el elemento " + element);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Se genera el siguiente error, al intentar verificar sección: " + e.getMessage());
        }
    }

    public static boolean visualizarObjeto(WebElement objeto, int segundos) {
        try {
            System.out.println("Buscamos el objeto:" + objeto + ", esperamos " + segundos + " segundos, hasta que aparezca.");
            WebDriverWait wait = new WebDriverWait(DriverContext.getDriver(), Duration.ofSeconds(segundos));
            wait.until(ExpectedConditions.visibilityOf(objeto));
            System.out.println("Se encontró objeto: " + objeto + ", se retorna true.");
            return true;
        } catch (Exception var3) {
            System.out.println("No se encontró objeto: " + objeto + ", se retorna false.");
            return false;
        }
    }

    public static void clickElemento(WebElement elemento) {
        try {
            elemento.click();
            Assert.assertTrue("Se hace click en el elemento", true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Se genera el siguiente error, al intentar hacer click en el elemento: " + e.getMessage());
        }
    }

    public static void insertarTexto(WebElement elementoInput, String texto) {
        try {
            if (visualizarObjeto(elementoInput, 30)) {
                elementoInput.sendKeys(texto);
                System.out.println("Se inserta el texto " + texto);
            } else {
                System.out.println("No se puede insertar texto");
                Assert.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Se genera el siguiente error, al intentar insertar texto: " + e.getMessage());
        }

    }

    public static void inciarSesion(WebElement elementMail, String mail, WebElement btnContinuar) throws Exception {
        try {
            if (validarElementoDesplegado(elementMail)) {
                elementMail.sendKeys(mail);
                btnContinuar.click();
                Assert.assertTrue("Se ingresa email y se presiona 'Continuar'", true);
            } else {
                Assert.fail("No se despliega campo email");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Se genera el siguiente error, al intentar iniciar sesión: " + e.getMessage());
        }
    }

    public static void esperar(int segundos) {
        System.out.println("Inicia la espera de " + segundos + " segundos;");
        long start = System.nanoTime();
        long end = 0L;
        long microseconds = 0L;
        long tiempoTranscurrido = 0L;

        do {
            end = System.nanoTime();
            microseconds = end - start;
            tiempoTranscurrido = TimeUnit.SECONDS.convert(microseconds, TimeUnit.NANOSECONDS);
        } while (tiempoTranscurrido < (long) segundos);

        System.out.println("Fin de la espera de " + segundos + " segundos;");
    }

    public static boolean validarElementoDesplegado(WebElement elemento) {
        try {
            Assert.assertTrue("Elemento encontrado", true);
            return elemento.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Se genera el siguiente error, al intentar validar elemento: " + e.getMessage());
            return false;
        }
    }

    public static void loadingNegado(WebElement element, int tiempo) {
        System.out.println("\nMétodo -> 'loadingNegado'\n");

        try {
            long start = System.nanoTime();
            long end = 0L;
            long microseconds = 0L;
            long tiempoTranscurrido = 0L;

            do {
                end = System.nanoTime();
                microseconds = end - start;
                tiempoTranscurrido = TimeUnit.SECONDS.convert(microseconds, TimeUnit.NANOSECONDS);
                if (tiempoTranscurrido >= 60L) {
                    Assert.fail("loading");
                }

                Duration.ofMillis(100L);
            } while (!visualizarObjeto(element, tiempo));

            Assert.assertTrue("Se encuentra el elemento en el periodo de tiempo", true);
        } catch (Exception e) {
            System.out.println("Se genera el siguiente error, al intentar obtener el código del correo: " + e.getMessage());
            e.printStackTrace();
        }

    }


}
