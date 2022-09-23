#### Project

###### Description

Microservice background management system

###### Technology stack

- Core
  - Spring cloud alibaba
  - LoadBalance
  - FeignClient
- Authentication
  - SpringSecurity
- Third party tools
  - HuTool

###### Architecture

- Four tier architecture (one more layer of management business logic compared with the three tier architecture)
- Controller: api interaction
- Service: Business logic layer
- Manage: public service implementation (the business logic in the service of the traditional three layers is extracted
  to this
  layer)
- Dao: database layer

###### System module

```
x-admin   
├── cloud-dependencies  //Public dependency on global version control
├── cloud-devtools      //development tools
├── cloud-framework     //public components
│       └── cloud-framework-core           //core component
│       └── cloud-framework-auth           //Authentication component
│       └── cloud-framework-entity-mybatis //mybatis support components
├── cloud-gateway  //gateway [127.0.0.1.com]
├── cloud-service-auth  //auth [127.0.0.1:8080]
├── cloud-services  //Subservices collection
│       └── user-service  //User service sub module
│               └── user-client  //External API of user service
│               └── user-app     //User service implementation [127.0.0.1:8090]
├──pom.xml  //project dependency
```

###### Dependent Services ([Here is the installation method of Docker](https://github.com/cuukenn/docker-scripts ))

- Alibaba Nacos Server: [How do I install it?](https://github.com/cuukenn/docker-scripts/nacos)
- Redis: [How do I install it?](https://github.com/cuukenn/docker-scripts/redis)
- MySql: [How do I install it?](https://github.com/cuukenn/docker-scripts/mysql)