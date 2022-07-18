# 1_Arquitectura Microservicios - JAVA - Spring Boot - Spring Cloud

El objetivo de este repositorio es brindar un primer paso o acercamiento a la arquitectura basada en microservicios sobre Spring Boot y Spring Cloud - JAVA.

Cabe aclarar que no soy ningún experto en el tema, soy un apasionado por el mundo de la tecnología por lo cual quiero aportar este repositorio con el objetivo de que gente inexperta en el tema, la cual quiera incursionar en este mundo, lo pueda hacer desde una perspectiva de una persona que también está iniciando y aprendiendo.

A continuación, se especificaron cada uno de los pasos realizados:

## 1 – Microservicios
El concepto de microservicios, proviene desde un enfoque arquitectónico para el desarrollo de software. Es decir, es un tipo de arquitectura que se emplea para desarrollar software.

Este tipo de arquitectura, tiene como fin, tener pequeños conjuntos de servicios definidos y agrupados, cada conjunto es independiente pero que, al comunicarse entre sí, representan un todo.

Por ejemplo, pensemos en un almacén/tienda, si empezamos a desglosar y a dividir dicho el negocio nos encontramos con que están los usuarios y toda la información relacionada a ellos, también nos encontramos con los productos y toda su información, por otro lado, están toda la información de las ventas y así podemos tener múltiples conceptos y conjuntos de datos. Que si los dejamos todos en un mismo conjunto de datos podríamos tener un conjunto demasiado grande y difícil de manejar, hablando en conceptos tecnológicos, si dejamos todos en una misma aplicación, tendríamos una aplicación demasiado grande lo cual dificulta la construcción.

Por otro lado el tener microservicios permite tener una aplicación distribuida e independiente, es decir, como cada microservicios es un conjunto de servicios independientes, el hecho de que un microservicio deje de funcionar, los demás deben seguir funcionando correctamente, escenario que no pasaría si tuviéramos todo en una misma aplicación.

Ahora, para este ejercicio, lo que realizamos fueron dos proyectos en spring boot los cuales serán nuestros microservicios (carpeta ->  1_microservices), uno de ellos simulara el microservicio de usuarios, y el segundo simulara el microservicio de productos. Lo que hicimos fue crear dos proyectos en spring boot y los configuramos de tal forma que pudiéramos tener dos API REST.

## 2 - Config Server
Los microservicios al ser proyectos de spring boot contienen un archivo de configuraciones el cual se encarga de contener todas las configuraciones necesarias para realizar diferentes tareas, como especificar el purto de levantamiento del microservicio, configuraciones de conexiones con bases de datos, y demas.

Supongamos que tenemos 2 microservicios, entonces tendriamos dos archivos de propiedades, cada uno en su respectivo microservicios, y supongamos que ahora necesitamos modificar una propiedad de configuracion, pues tendriamos que abrir cada uno de los microservicios, realizar los cambios, guardar los cambios, y desplegar nuevamente el microservicio... Facil. Pero ahora supongamos que no son 2 microservicios, si no 10, o 100, o mas, se vuelve una tarea demasiado tediosa tener que abrir cada microservicio para realizar la respectiva configuracion y volver a levantar el microservicio.

Ahora, supongamos un escenario donde tengamos todos los archivos de configuracion centralizados en un mismo lugar, y a la hora de levantar cada microservicio, este se encargue de consultar su configuracion para ejecutarse. Todo suena mejor, el hecho de tener estas configuraciones centralizadas, permiten un mantenimiento mas facil y rapido. Pues de esto se trata el concepto de config-server. De tener un servidor de configuraciones y todos los microservicios que quieran ejecutarse, consulten su respectiva configuracion en tal servidor. config-server es una de las tantas herramientas de spring-cloud.

Para logar esto, creamos un proyecto spring-boot (carpeta -> 2_server_config/configserver), el cual se convertira en el servidor de configuraciones por medio de la dependencia :
    
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-config-server</artifactId>
	</dependency>
	
a continuacion, se tiene que habilitar el servidor por medio de la etiqueta:

    @EnableConfigServer
	
Esta etiqueta se tiene que poner en la clase principal donde se encuentra el metodo main, junto con la etiqueta de "@SpringBootApplication".

por ultimo tenemos que codificar el archivo de configuraciones del config-server. Pero antes de continuar, cabe resaltar que los proyectos por defecto traen un archivo llamado application.properties, en el cual se registrar todas las configuraciones de la aplicacion, y la hora de ejectar la aplicacion se leen todas estas configuraciones de tal archivo. Pero cuando se trabajo con spring-cloud, es necesario tener varias propiedades cargadas previamente a la ejecucion, es por esto que existe otro archivo llamado bootstrap (puede tener la extencion .properties o .yml segun la preferencia del desarrollador), este archivo se lee previo a la lectura del archivo application por lo que es importante que cuando se trabaje con spring-cloud se tenga en cuenta esto y se haga el cambio del archivo application por el archivo bootstrap. Tambien es necesario tener la siguiente dependiencia para trabajar con el archivo bootstrap:

    <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-bootstrap</artifactId>
		<version>3.1.3</version>
	</dependency>

una vez se haga tal cambio de archivo, se debe indicar lo siguiente en el archivo bootstrap.yml:

    server:
  		port: 8081

	spring:
		application:
			name: config-server
		cloud:
			config:
				server:
 					git:
 						uri: https://github.com/dtrujilloc/1_tuto_microservices
 						searchPaths: 2_server_config/configdata
 						default-label: "master"
						
Un paso adicional que realizamos en este proyecto de config server fue agregarle seguridad de acceso, es decir, que cualquier que quiera solicitar informacion al config-server debe enviar las credenciales correctas. Esto lo logramos con la dependencia:

    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>

Y especificados tales credenciales en archivo de configuracion de bootstrap.yml:

	spring:
		security:
			user:
				name: root				# nombre de usuario preferido
				password: secret		# contraseña preferida

Una vez realizada la configuraciones del config server, tenemos que proceder con la configuracion de los microservicios como config-client para que cuando se ejecten consulten el archivo de configuraciones en el server que creamos anteriormente. Para esto tenemos que agregar las siguientes dependencias en cada uno de los microservicios:

    <!-- dependencia de config client -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-client</artifactId>
			<version>3.1.3</version>
		</dependency>

		<!-- dependencia de bootstrap para spring cloud -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bootstrap</artifactId>
			<version>3.1.3</version>
		</dependency>

el siguiente paso es convertir el archivo application.properties en el archivo de bootstrap.yml. Y por ultimo agregar la configuracion correspondiente para convertirlo en un config-client:

	# Archivo de configuracion de config-client para el microservicio de usuarios
	spring:
		application:
			name: ms-user

		cloud:
			config:
				enabled: true
				uri: http://localhost:8081		# URI del config-server
				username: root
				password: secret

El ultimo paso es crear los archivos de configuracion de cada uno de los microservicios, ya que el microservicio le preguntara al sevidor que le de devuelva un archivo de configuraciones para ejecutar el microservicio. Para esto debemos crear un archivo de configuracion pro cada microsevicio con el mismo nombre que se le indico el archivo bootstrap.yml de configuracion como cliente. Por ejemplo en el ejemplo anterior, se indico que la aplicacion tomaria el nombre de "ms-user", pues el archivo de configuracion para ese microservicio se tiene que llamar "ms-user.yml". Estos archivos se encuentran en la carpeta "2_server_config/configdata".