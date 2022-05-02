
#@LB-122
Feature: Ingresar a login consorcio, Se espera ingresar al login de consorcio pasando por banca y seguros.
		
    Background:
		Given ingreso a la aplicacion web y navego a la url de consorcio
		
		@TEST_LB-123 @regression 
		Scenario Outline: Ingresar login
			When ingreso a tu cuenta
			And banca y seguros
		  Then se visualizan los campos del login "<rut>" y "<clave>"
		
				Examples:
				|rut |clave|
				|rut |clave|

	 @TEST_LB-542 @regression 
	 Scenario: Ingresar portabibilidad fianciera
		When ingreso a portate o hazte cliente  
		And  se selecciona portabilidad financiera
	  Then se visualiza el home de portabilidad financiera
