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
