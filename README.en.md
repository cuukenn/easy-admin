#### Project

###### Description

Microservice background management system

###### Technology stack

- Core
  - Spring cloud alibaba
  - LoadBalance
  - FeignClient
- Authentication
  - Sa-token
  - OAuth
  - IdToken
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
├── cloud-gateway  //gateway [gateway.cloud.com]
├── cloud-auth client  //OAuth client [client.auth.cloud.com:8080]
├── cloud-auth server  //OAuth server [server.auth.cloud.com:8081]
├── cloud-services  //Subservices collection
│       └── user-service  //User service sub module
│               └── user-client  //External API of user service
│               └── user-app     //User service implementation [user.service.cloud.com:8090]
├──pom.xml  //project dependency
```

###### Dependent Services ([Here is the installation method of Docker](https://github.com/cuukenn/docker-scripts ))

- Alibaba Nacos Server: [How do I install it?](https://github.com/cuukenn/docker-scripts/nacos)
- Redis: [How do I install it?](https://github.com/cuukenn/docker-scripts/redis)
- MySql: [How do I install it?](https://github.com/cuukenn/docker-scripts/mysql)

###### Host file modification (for cookie multi system scope transfer)

```
# x-admin cloud host
127.0.0.1 gateway.cloud.com
127.0.0.1 client.auth.cloud.com
127.0.0.1 server.auth.cloud.com
127.0.0.1 user.service.cloud.com
```