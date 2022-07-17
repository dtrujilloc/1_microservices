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