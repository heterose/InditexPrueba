## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Architecture](#architecture)
* [Code Explanation](#Code-Explanation)
* [Integration and Unit Tests](#Integration-and-Unit-Tests)

## General info
<hr/>This application/service is a SpringBoot application that provides a rest query endpoint which accepts as input
the following parameters:

* Apply date on product (which is a LocalDateTime java type)
* Product identifier (Identifies a type of product as unique product for the database)
* Group chain identifier : which default value is 1 (ZARA)

This endpoint will return a JSON as output which contains the following data:

* Product identifier
* Product chain identifier (if it is not sent on demmand, default value will be assigned (ZARA=1)
* Fee to apply
* Rate (between dates) to apply a price for a product
* Final price to apply for that product.

The point of this application is to emulate and access a company's electronic commerce database in which we can
find data that reflects the final price and fee to be applied for certain product having a date rate that applies to a product,
and for a certain product brand. In this way, the final price that should be applied to a product
is obtained depending on these conditions.


## Technologies
Project is created with:
* IntelliJ IDEA 2023.3.2 (Community Edition)
* Java JDK 17
* Spring boot API ver. 3.2.2
* An in-memory H2 database which contains a PRICES table and is created on application startup
* Apache Maven 3.9.5
* Project version: 1.0.0 (Since it is the first approach to this application)


## Setup
In order to run this project locally follow this steps:
* Download and install Java JDK 17 from website https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html.
* Download and install maven from website https://maven.apache.org/download.cgi.
* Download this application from GIT, unzip the code to a preferred folder
* Open a shell or command-line window and move to the application folder you´ve
  just unzipped. Get into the root folder and execute the following commands:

      $ mvn clean install
      $ mvn spring-boot:run

If you just want to install and run the application skipping the tests, run the following
commands:

      $ mvn clean install -DskipTests
      $ mvn spring-boot:run

## Architecture
<hr/>This simple application follows a layer-based software model. Layered architecture is a software design model, the basis 
of which is the separation of the different functionalities of the system into layers or levels, where each layer is in 
charge of a set of specific tasks and communicates with adjacent levels through well-defined interfaces. .

Each layer is built on top of the layer immediately below it, which provides the services and functionality necessary 
for the layer above it to function correctly. This model allows for better organization and modularity of the system; 
It also facilitates its long-term maintenance and evolution.
<br/><br/>
<h4>Elements of layered architecture used in this application</h4>

A layer is a set of software components or modules that are responsible for performing a specific function within the system. 
Each layer or level communicates with adjacent layers through a well-defined interface, allowing separation of 
responsibilities and modularity of the system. 

In general, layers are organized hierarchically; the lower one taking care of the implementation details and the upper 
one providing the user interface and presentation logic.

Each layer in this application carries its own responsibilities, each of them are described below. 

 * The <b>presentation layer</b> (or user layer):
   In the case of this application, and since it is a rest endpoint, where there is no presentation layer, in the 
   integration tests carried out locally, a rest API client such as Postman is used, in some way, to emulate the 
   presentation layer a front-end application or a simple page that user may use to call this application endpoint. 

   The controller of this application (implemented as Spring boot rest controller PricesController) that is responsible for 
   receiving such requests from a front-end will be considered as component of the presentation layer (Based on the J2EE 
   pattern catalog which defines a pattern called Front Controller, which resides in the Presentation layer patterns group). 
   As said before, Postman will be used to perform integration tests, it is responsible for calling the controller 
   (endpoint) and pass data to this controller.
   <br/><br/>
   To validate that the input data is correct, the controller uses a component that is responsible for validating
   data received from the user interface, before passing it to the corresponding component of the business layer.

    <br/><b/>

 * The <b>business logic layer</b>:
   Due to the simplicity of this application, the business layer contains just one component implemented through a service (PricesService) ,
   for such reason, there is no implemented interface between controller and service ). A service is a logical unit of functionality that 
   is provided through a well-defined interface.
   The service in this bussiness logic layer is responsible for receiving the data from the controller and send the 
   responses to it.<pre>
   <br/><b style="font-size:12px;">NOTE As said before, due to simplicity of this project, an interface will not be implemented, but It will be 
   necessary when commercial modular applications are developed and it is necessary to decouple, as much as possible,
   the dependencies that exist between components that interact between the different layers).  In this application, we 
   chose to use spring boot because it is a framework that implements the dependency injection pattern and allows the 
   development of stand-alone applications where the conponents (layer components) are decoupled as much as possible by 
   injecting dependencies between them at the necessary time.
   </b></pre>
<br/>
   * The <b>data access layer</b> is the layer that contains the components necessary to perform all accesses to the database. 
   This layer receives data from the business logic components and with that data performs the corresponding operations 
   on the database.<br/><br/>
   In the case of this application, there is a service, which is a component of the business logic and will be responsible 
   for performing the dependency injections necessary to use the components of the database access layer.<br/><br/>

      This application uses an H2 database that resides in memory and is created when the application is run (there are two scripts,
    specifically, one to create the table and another to fill it with test data). When the application is executed, these two scripts are also loaded. 
<br/>
      To perform operations on the database, a relational object model is used, it is responsible for performing the necessary 
      operations on the database.<br/><br/>

      The orm used is JPA and is included within the spring boot framework itself.
      An interface (PricesRepository) has been created and defines the data search functionality on the created table.

## Code Explanation
<hr/>The following figure shows the package structure and the classes it contains.<br/><br/>

<img height="400px"  width="500px" src="https://github.com/heterose/InditexPrueba/blob/prueba/src/main/resources/images/package-structure.png"/>
<br/><br/>The following table offers a short description of each of the application classes
<div style="width:670px; height=500px"> <pre>
controller
    <b>PricesController.java</b>: This class implements the controller and contains 
    the endpoint (GET) that will be called through the user interface layer 
    with the corresponding parameters.

dto
    <b>InditexPriceDTO.java</b>: A data transfer object has been generated to decouple the use of the 
    database entity (InditexPrice) in the different layers.

entity
    <b>InditexPrice.java</b>:JPA entity that stores the data returned by the componente 
    of the data access layer to the business logic layer.

exception
     <b>InvalidInputDataException.java</b>: This exception is created to report an error 
     in the data entered by the user when the user makes a call to the controller endpoint.
     <b>DataNotFoundException.java</b>: This exception has been created to inform the user 
     when no correct price is found with the chosen search criteria
     <b>PriceControllerAdvice.java</b>: This class has been created to globally handle errors 
     that can occur at runtime, such as "Price not found" or "Invalid input data.".

mapper
     <b>PriceMapper.java</b>: A mapper has been created to convert the InditexPrice entity 
     into InditexPriceDTO, in this way the database entity is decoupled from the rest of the 
     layers, converting it to a service-side entity.
      
    
repository
      <b>PricesRepository.java</b>: This class is a component of the data access layer and 
      defines the functionality necessary to perform the data query operation on the prices table.
      This component is responsible for filling with data the entities of the InditexPrice.java 
      type used in each search. <br/>

validator
      <b>PricesValidator.java</b>: This component is used by the controller and is responsible for 
      validating the input data issued by the user when making the call to the controller endpoint.<br/>
      This component could also be implemented on the business logic layer side so that it is used 
      by the PricesRepository service.

<b>CommerceApplication.java</b>: This is the class in charge of loading the application context and 
      start this spring boot application.
</pre></div>

## Integration and Unit Tests

The unit tests are located in the tests package within the src package (package -> unit), they have been carried out 
to test the functional units of the application.<br/>
The integration tests are located in the tests package within the src package (package -> integration).<br/>
they have been carried out to test multiple layers of applications in a single test. They verify whether the controller 
and persistence layers work together correctly or not.<br/><br/>
The following figure shows the tests carried out in this application.

<img height="300px"  width="400px" src="https://github.com/heterose/InditexPrueba/blob/prueba/src/main/resources/images/tests.png"/>
<br/>
The application was run locally using the Tomcat container embedded with spring boot.

The container (tomcat) runs at runtime and deploys automatically this rest API so that it can be executed from the UI layer.

To emulate a client that uses the controller endpoint, Postman is used as the API client.
The following image shows the call to the endpoint performed by Postamn API client
<br/><br/>
<img height="400px"  width="600px" src="https://github.com/heterose/InditexPrueba/blob/prueba/src/main/resources/images/postman.png"/>



