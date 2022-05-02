Feature: Ingresar a portabilidad financiera
	Se espera ingresar al home de portabilidad financiera

Background:
  Given ingreso a la aplicacion web y navego a la url de consorcio
 
@regression 
Scenario: Ingresar portabibilidad fianciera
	When ingreso a portate o hazte cliente  
	And  se selecciona portabilidad financiera
  Then se visualiza el home de portabilidad financiera

