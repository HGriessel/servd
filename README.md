# Servd

Grails Application Docker Setup

This guide will help you build, set up, and run the Grails-based application with PostgreSQL using Docker.

## Prerequisites

Make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Build Docker environment

### Step 1: Clone the Repository

```bash
git clone https://github.com/HGriessel/servd.git
cd servd

```

### Step 2: Build the Environment

```bash
docker compose up -d
```

```
 ✔ Network services_default       Created                                                                                                                                                                         0.1s
 
 ✔ Container services-db-1        Started                                                                                                                                                                         0.4s
 
 ✔ Container services-servd-fe-1  Started                                                                                                                                                                         0.4s
 
 ✔ Container services-servd-be-1  Started
```

| Note that the current build of the application takes approximately 15-20min as all relevant images are fetched and build

Confirm that all services are running

```bash
docker compose ps
```
```
NAME                  IMAGE               COMMAND                  SERVICE    CREATED         STATUS         PORTS

services-db-1         postgres:13         "docker-entrypoint.s…"   db         3 minutes ago   Up 3 minutes   5432/tcp

services-servd-be-1   services-servd-be   "java -Dgrails.env=p…"   servd-be   3 minutes ago   Up 3 minutes   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp

services-servd-fe-1   node:16             "docker-entrypoint.s…"   servd-fe   3 minutes ago   Up 3 minutes   0.0.0.0:8081->8080/tcp, [::]:8081->8080/tcp
```

### Step 3:

You can then access the service on http://localhost:8081


## API Documentation 

You can find the API documentation in the form of a Postman collection at [servd API Docs](https://github.com/HGriessel/servd/tree/main/Postman)
## Decisions and Assumption

1. Authentication was not part of the project scope. So was not implemented.
2. I decided on Postgres solely due to my familiarity with the database
3. I used VueJS and tailwind also for my level of experience with the tools. 
4. I decided to showcase my abilities with containers by using a docker-compose environment
  - This has the major benefit of being able to deploy the same build repeatable regardless of the host machine's environment
4. From the Grails [documentation](https://grails.github.io/legacy-grails-doc/4.1.3/guide/) there are 3 ways to handle restfull aplication.
   1. [native/bare Controller](https://grails.github.io/legacy-grails-doc/4.1.3/guide/theWebLayer.html)
   2. [domainResources](https://grails.github.io/legacy-grails-doc/4.1.3/guide/REST.html#domainResources)
   3. [restfulControllers](https://grails.github.io/legacy-grails-doc/4.1.3/guide/REST.html#restfulControllers)
   
    Due to the nature of the Assignment being to test my development skills choose 2 of the above methods to implement the CRUD functionality so as not to rely on the scaffolding/boilerplate

    1. [**ChurchService**](https://github.com/HGriessel/servd/blob/main/grails-app/controllers/church/servd/services/ChurchServiceController.groovy)  implements "native/bare" Controller
    2. [**ChurchServiceType**](https://github.com/HGriessel/servd/blob/main/grails-app/controllers/church/servd/services/ChurchServiceTypeController.groovy) I implemented RestfulController

5. If I were to cater the ChurchService and ChurchServiceType models more specifically to Unite180 I would move the location property to ChurchService and have ChurchServiceType represent services including Sunday Services, Homerun, Leadership Events, uschool events etc.
   I would also then rename the models to **Service** and **ServiceType** but this is a minor adjustment to try and avoid confusion
   - This would enable more dynamic use of the Models and help prevent database table sprawl.
6. Eager Loading, Lazy Loading using lazy loading due to the small nature of the application


 

## TODO 
1. --Return whether delete was successful or not--
2. Update does not validate incorrect field names ie if you pass names instead of names it will be ignored with no warning
  - however, when the updated object is returned responsibility can sit on the client side to ensure correct updates were made
4. Pagination for index/list endpoints
5. --Datetime HH:MM not setting correctly from Vue.JS--
6. grails dockerastion looks at using the run app for the development environment to add the ability to hot reload 
  - building for prod takes approx 10min need to get addressed
7. Eager Loading, Lazy Loading using lazy loading due to the small nature of the application



