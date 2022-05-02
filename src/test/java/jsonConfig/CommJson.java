package jsonConfig;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommJson {
	private URL url = null;
	private HttpURLConnection urlOC = null;
	private StringBuilder builder = null;

	/**
	 * Function: getJson Description: Accesa al servicio Rest mediante la Url entregada
	 * 
	 * @param String
	 *            urlSrv
	 * @return void
	 **/
	public void setConn(String urlSrv) {
		try {
			url = new URL(urlSrv);
			urlOC = (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Agrega paramaetros a url en el formato ?clave=valor
	 * @param urlSrv url a consumir
	 * @param params key value a agregar en url
	 * @throws Exception 
	 */

	public  void setConnParams(String urlSrv, LinkedHashMap<String, String> params) throws Exception {
		String url = urlSrv+"?";
		boolean first = true;
		for (Map.Entry<String, String> entry : params.entrySet()) {
			if(first) {
				first = false;
				url += entry.getKey()+"="+entry.getValue();
			}else {
				url += "&"+entry.getKey()+"="+entry.getValue();
			}
		}
		this.setConn(url);
	}
	/**
	 * Function: getJsonData Description: Retorna una cadena con formato JSON, mientras el status de servicio sea correcto(200, 201, etc.)
	 * 
	 * @return String
	 **/
	public String getJsonData() {
		try {
			String line;
			builder = new StringBuilder();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(urlOC.getInputStream()));
			while ((line = bfr.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			builder.append("");
		}
		return builder.toString();
	}

	/**
	 * Function: getJsonDataError Description: Retorna una cadena con formato JSON, mientras el status de servicio sea incorrecto(500, 400, etc.)
	 * 
	 * @return String
	 **/
	public String getJsonDataError() {
		try {
			String line;
			builder = new StringBuilder();
			BufferedReader bfr = new BufferedReader(new InputStreamReader(urlOC.getErrorStream()));
			while ((line = bfr.readLine()) != null) {
				builder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			builder.append("");
		}
		return builder.toString();
	}

	/**
	 * Function: getResponseCode Description: Retorna el codigo de respuesta http
	 * 
	 * @param void
	 * @return int
	 **/
	public int getResponseCode() {
		int response = 0;
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) urlOC;
			response = httpConnection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			response = 0;
		}
		return response;
	}

	/**
	 * Function: getResponseCode Description: Retorna el header de respuesta http
	 * 
	 * @param String
	 *            que se necesita obtener
	 * @return String
	 **/
	public String getResponseHeader(String header) {
		String headerResponse = "";
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) urlOC;
			headerResponse = httpConnection.getHeaderField(header);
		} catch (Exception e) {
			e.printStackTrace();
			headerResponse = "";
		}

		return headerResponse;
	}

	/**
	 * Setear parametros para servicios que van por POST
	 * 
	 * @param parametros
	 *            Patrametros que recibe por via POST
	 */
	public void postParametros(String parametros) {
		try {
			DataOutputStream wr = new DataOutputStream(urlOC.getOutputStream());
			wr.writeBytes(parametros);
			wr.flush();
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setear metodo a utilizar como GET o POST, etc
	 * 
	 * @param metodo
	 *            Metodo a utilizar
	 */
	public void setRequestMethod(String metodo) {
		try {
			((HttpURLConnection) urlOC).setRequestMethod(metodo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setear parametros de header
	 * 
	 * @param key
	 * @param value
	 */
	public void setRequestProperty(String key, String value) {
		try {
			urlOC.setRequestProperty(key, value);
			urlOC.setDoOutput(true);
			urlOC.setDoInput(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Para el caso de parametros POST que no tengan un body json como estructura, como por ejemplo estructura: x-www-form-urlencoded estos deben ser constuidos con la estructura
	 * key1=value1&key2=value2, este metodo se encarga de escribirlos de esta manera y concatenar con &
	 * 
	 * @param params
	 *            key, value
	 * @return Estructura con key y value concatenada de forma correcta
	 */
	public String getDataString(Map<String, String> params) {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (first) 
					first = false;
				else
					result.append("&");
				
				result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
				result.append("=");
				result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result.append("");
		}

		return result.toString();
	}

	/**
	 * Deshabilita el certificado SSL, en algun caso que sea necesario, este metodo debe ser llamado antes de ingresar la URL de endpoint
	 * 
	 * @return void
	 */
	/*
	public void doTrustToCertificatesSSL() throws Exception {
		doTrustToCertificates("SSL");
	}
	*/

	/**
	 * Deshabilita el certificado, en algun caso que sea necesario, este metodo debe ser llamado antes de ingresar la URL de endpoint
	 * 
	 * @param sslContext(opcinal)
	 *            Tipo de certificado a deshabilitar, en caso de no estipular, por defecto sera SSL
	 * @return void
	 */
	/*
	public static void doTrustToCertificates(String sslContext) throws Exception {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				return;
			}
		} };
		SSLContext sc = SSLContext.getInstance(sslContext);
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					System.out.println("Warning: URL host '" + urlHostName + "' es diferente al host SSLSession '" + session.getPeerHost() + "'");
				}
				return true;
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}
	*/
	/**
	 * Function: closeSession Description: Finaliza conexion http
	 * 
	 * @return void
	 **/
	public void closeSession() throws IOException {
		HttpURLConnection httpConnection = (HttpURLConnection) urlOC;
		httpConnection.disconnect();
	}
}