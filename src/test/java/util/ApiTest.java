package util;

import java.util.Map;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;


public class ApiTest {

        public static void anularCitas(String rut) {
        	
        	if(!rut.isEmpty()) {
        	String requestBody = "{\n" +
    	            "  \"nid\":\""+rut+"\" \n}";
        	try {
        		
        		String complemento = "/api/portal/reservations/"+retornarReservationId(rut)+"/cancel";
        		
        		if(retornarReservationId(rut) !=null) {
        	    RestAssured.baseURI = "";
        	    Response response = given()
        	    		.header("apiKey","")
        	    		.header("Content-type", "application/json")
                        .and()
                        .body(requestBody)
                        .when()
                        .post(complemento)
                        .then()
                        .extract().response();
        	    assertEquals(200, response.statusCode());
        	    
     
        		}
    
			} catch (Exception e) {
				System.out.println("Error al anular");
				Assert.fail("Error al anular "+e.getMessage());
			}
        	}
        	
        }

        public static Map<Object, Object> consultarReservas(String rut) {
        	Map<Object, Object> listado = null;
        	try {
        		
        		RestAssured.baseURI = "";
        		Response response = given()
        				.header("apiKey","")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/portal/reservations/1/"+rut+"")
                        .then()
                        .extract().response();

        		
        		listado = response.jsonPath().getMap("resource[0]");
                assertEquals(200, response.statusCode());
                //String usernames = response.jsonPath().getString("resource[0].entity_name");
  
			} catch (Exception e) {
				System.out.println("Error al obtener los datos");
				Assert.fail("Error al obtener los datos "+e.getMessage());
			}

			return listado;
        }

        public static int retornarCodigoRespuesta(String rut) {
        	int statusCode = 0;
        	try {
        		
        		RestAssured.baseURI = "";
        		Response response = given()
        				.header("apiKey","")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/portal/reservations/1/"+rut+"")
                        .then()
                        .extract().response();

        		statusCode  = response.statusCode();
			} catch (Exception e) {
				System.out.println("Error codigo distinto a 200");
				Assert.fail("Error codigo distinto a 200 "+e.getMessage());
			}

			return statusCode;
        }


        public static String retornarReservationId(String rut) {
        	String reservation_id = null;
        	try {
        		
        		RestAssured.baseURI = "";
        		Response response = given()
        				.header("apiKey","")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("/api/portal/reservations/1/"+rut+"")
                        .then()
                        .extract().response();

        		reservation_id = response.jsonPath().getString("resource[0].reservation_id");
			} catch (Exception e) {
				System.out.println("Error codigo distinto a 200");
				Assert.fail("Error codigo distinto a 200 "+e.getMessage());  
			}

			return reservation_id;
        }
}
