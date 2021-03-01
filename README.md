### General Info
***

Backend for frontend. It has been developed to give access to suscripcion Microservice methods. 

There are 4 different methods, running on port 8080:
* "/adidas/createSubscription" (Post) --> Create a new subscription. 
* "/adidas/deleteSubscription/{id}" (Delete) --> Delete the given subscription
* "/adidas/subscriptions/{id}" (Get) --> List the given subscription
* "/adidas/subscriptions" (Get) --> List all the subscriptions

More info in http://localhost:8080/swagger-ui.html#/bff-controller

## Technologies
***
A list of libraries used within the project:
* [spring-boot-starter-web]: Used for restfull webservices with spring and a tomcat included
* [spring-boot-devtools]: Used for development. It allows you to run easily the application in the embedded tomcat.
* [springfox-swagger2]: Used for Swagger documentation
* [springfox-swagger-ui]: Used for Swagger html interface
* [spring-boot-starter-data-jpa]: Provide database persistence.
* [mysql-connector-java]: Give access to MySql databases.
* [spring-boot-starter-mail]: Used for sending mails.

## Installation
***
Running using docker.
Docker is an open platform for building, shipping and running distributed applications. Follow steps on the site to install docker based on your operating system.

The bffAdidas image depends on several images. The suscripcionAdidas image and mailAdidas image must also be created, if not yet.

Create images:
* From MicroserviciosWorkspace/mailAbsis folder run the command "docker build -t mailabsis ."
* From MicroserviciosWorkspace/suscripcionAbsis folder run the command "docker build -t suscripcionabsis ."
* From MicroserviciosWorkspace/bffAbsis folder run the command "docker build -t bffabsis ."

Create containers:
* From MicroserviciosWorkspace folder run the command "docker-compose up"


