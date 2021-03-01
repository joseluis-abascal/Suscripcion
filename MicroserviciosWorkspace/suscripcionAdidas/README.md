### General Info
***

Backend for frontend. It has been developed to give access to suscripcionAdidas Microservice methods. 

There are 4 different methods, running on port 8081:
* "/subscription/insert" (Post) --> Create a new subscription. 
* "/subscription/delete/{item}" (Delete) --> Delete the given subscription
* "/subscription/list/{item}" (Get) --> List the given subscription
* "/subscription/list" (Get) --> List all the subscriptions

## Technologies
***
A list of libraries used within the project:
* [spring-boot-starter-web]: Used for restfull webservices with spring and a tomcat included
* [spring-boot-devtools]: Used for development. It allows you to run easily the application in the embedded tomcat.
* [spring-boot-starter-data-jpa]: Provide database persistence.
* [mysql-connector-java]: Give access to MySql databases.

## Installation
***
Run it using docker.
Docker is an open platform for building, shipping and running distributed applications. Follow steps on the site to install docker based on your operating system.

The suscripcionAdidas image depends on mailAdidas image, so it must also be created, if not yet.

Create images:
* From MicroserviciosWorkspace/mailAbsis folder run the command "docker build -t mailabsis ."
* From MicroserviciosWorkspace/suscripcionAbsis folder run the command "docker build -t suscripcionabsis ."

