# Spring boot + Swing interface
Integration testing between java desktop application in swing as frontend and backend in spring-boot. 
Middleware based in REST API, basic CRUD provided.

### Version
1.0.0

### Description & Arquitecture
Two related projects which can work almost independand each other. 
Frontend project contains an easy Swing gui with the basic CRUD.
Backend project contains all business loginc behind a set of REST entry points.
Plain JSON it's exchange between both projects.

#### Design notes
- Unique controller for manage all the entry points. Due it's an small project, this solution gives easily maintenance. When the project grows up split into different controllers will be required.
- Data layer it's managed by Spring Data, so, instead DAO pattern, repositories automatically created by Spring takes the responsability for retrive and save the data.
- Small service layer contains bussiness logic, it's not needed to add factories or complex build system due the small of the project.

### Install and run
Both projects are maven based. Dependencies, build and testing are automaticalled managed by it.
In order to build next command need to be run in both projects
```sh
$ mvn clean install
```
Frontend project runs as a normal java application :
```sh
$ java -jar frontend-1.0.0-SNAPSHOT.jar
```
Backend project can be run as a war file deployed in a tomcat/jetty/... or can be run as a spring-boot application :
```sh
$ mvn spring-boot:run
```
### Instructions
It's highly recommended run first the backend and then launch the client. The current state of art doesn't verify if the backend it's running or not. That will be a good improvement :)

The backend has the following entry points defined:
Method GET – Retrieve information
- Find a specific element
  - /backend/v1/find/customer/{code}
  	- {code} Customer code which identifies it uniquely
  - /backend/v1/find/product/{code}
    - {code} Product code which identifies it uniquely
  - /backend/v1/find/salesorder/{orderNumber}
    - {orderNumber} numeric code for the order which identifies it uniquely

- Retrieve a group of elements
  - /backend/v1/get/all/customer : retrieve all the available customers.
  - /backend/v1/get/all/product : retrieve all the available products.
  - /backend/v1/get/all/salesorder/{code} : Retrieves all the sales order of the specified customer.
  - /backend/v1/get/all/salesorder : retrieve all the available sales orders.

Method POST – Create/Update elements
- Create and update info
  - /backend/v1/add/product
  - /backend/v1/add/customer
  - /backend/v1/add/salesorder
  - /backend/v1/add/orderline

DELETE – Mark an element as removed
- Delete entities
  - /backend/v1/delete/customer/{code}
    - {code} Customer code which identifies it uniquely
  - /backend/v1/delete/product/{code}
    - {code} Product code which identifies it uniquely
  - /backend/v1/delete/salesorder/{orderNumber}
    - {orderNumber} numeric code for the order which identifies it uniquely

### Development
Want to contribute? Great!

License
----
MIT


**Free Software, Hell Yeah!**