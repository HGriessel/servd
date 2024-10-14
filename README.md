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


 ✔ Network services_default       Created                                                                                                                                                                         0.1s
 ✔ Container services-db-1        Started                                                                                                                                                                         0.4s
 ✔ Container services-servd-fe-1  Started                                                                                                                                                                         0.4s
 ✔ Container services-servd-be-1  Started

| Note that the current build of the application takes aproxinatly 15-20min as all relevant images are fetched and build

Confirm that all services are running

```bash
docker compose ps
```

NAME                  IMAGE               COMMAND                  SERVICE    CREATED         STATUS         PORTS
services-db-1         postgres:13         "docker-entrypoint.s…"   db         3 minutes ago   Up 3 minutes   5432/tcp
services-servd-be-1   services-servd-be   "java -Dgrails.env=p…"   servd-be   3 minutes ago   Up 3 minutes   0.0.0.0:8080->8080/tcp, :::8080->8080/tcp
services-servd-fe-1   node:16             "docker-entrypoint.s…"   servd-fe   3 minutes ago   Up 3 minutes   0.0.0.0:8081->8080/tcp, [::]:8081->8080/tcp


### Step 3:

You can then access the service on http://localhost:8081


## API Documentation 

You can find the API documentation in the form of a Postman collection at [servd api]()


## TODO 

1. Return whether delete was successgull or not
2. update does not validate incorrect field names ie if you pass names instead of name it will be ignored with no warning
  - however the updated object is returned responsibilty can sit on the client side to ensure correct updates was made
3. Had to set Srevice controller to prefer sending json over all else
  - due to best practices with GET methods not inbcluding Content-type
4. Pagination for index/list endpoints
5. --Datetime HH:MM not setting correctly from Vue.JS--
6. grails dockerastion look at using run app for development environment to add the abilty to hot reload 
  - building for prod takes aprox 10min need to get addressed
7. Eager Loading, Lazy Loading using lazy loading due to small nature of the application



