# My Vision of Clean Architecture and Domain-driven Design using microservices and AWS

In this project, I use a <b>Hexagon</b> as a boundary between an application and the outside world. Inside the 'hexagon', I use a mix of simplified <b>Domain-Driven Design</b> (DDD) with <b>Clean</b>-Architecture (The 'infrastructure' and 'use case' logic is grouped into separate layers).

The "Clean" (Layered) Architecture is another way to provide <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units according to their domain and functional roles within the application.
(This approach ensures that the architecture is easy to maintain and extend, as changes to one layer do not affect the others)

### To achieve this vision, I am refactoring my old 'Hotel-interaction' project, in order to encapsulate domain-data (using Domain-driven Design and Clean Architecture principles as Onion and Hexagonal), which will enable us to create a modular and scalable system that can easily be adapted to future changes and enhancements.

![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)
![Aggregate root](/docs/img/ddd.webp)

### Layer Structure:

1. <b>Application</b> - the boundary between the core application logic and the external world.

   - <b>Domain.model</b> layer
     - To simplify the implementation, we are handling Interfaces of <b>Domain-entities</b> at the <b>Domain-Layer</b>. 
     <sup><sub>In Clean-architecture,  it is best practice to separate JPA entities from domain entities  and use them only in the <b>Infrastructure or Adapter-Layer</b> (with JPA repositories). This separation ensures, that the two types of entities do not depend on each other, but this would require a double declaration of same entity-fields in different layers. While this may seem redundant, it ensures that the domain layer is not tightly coupled to the JPA infrastructure layer, which would make the system less flexible and harder to maintain in the long term.</sup></sub>

   - <b>UseCase</b> layer
     - <b>Separation of usecase-logik-methods from technical (low-level) service-help-methods</b>
       - Usecases are <b>'interactors'</b> with Domain-Aggregates endpoints: create Obj, findByName...)
         - 1.define own (or use same level-) DTO with <b>lower</b> layers (UserDTO)
           - Or just via own Interfaces-Generics, instead of own DTO: e.g. class UseCase<T: IUser, Z: IUserDTO> 
         - 2.define own (or use same level-) help-IServices ('IUserService') 
           - e.g. 'UserRegisterINTERACTOR' (uses injected IUserService) and implements <b>custom usecases</b> steps based on i-help-infrastructure-methods
           - Problem: used IServices not anemic calls (ServiceImpl.save() dummy call to low-level-Repository.save())

   - <b>Infrastructure</b> layer
     - Implementation of (technical) help-IServices (UserServiceImpl), that was declared in- and will be injected in-INTERACTOR
       - In fact lowest level: only Repository(Service-translator: Dto<--to-->Repository), Controller(Rest-Api), API-Handler-Exceptions, AWS(data actions) (??). Another services are higher level?
       - Custom implementations for interfaces of upper layer (IServices) : (help-IServices implementation)
       - We can update (swap) this custom implementation package with another one, <b>but we don't change upper layer</b> logik!
     - Implementation of (port) interfaces, that was declared for outside adapters

   - <b>Port</b> layer: interfaces for adapters (public boundaries)


2. <b>Adapter</b> layer
    - <b>in</b> adapters:
        - specific user request (Controller Adapters)
    - <b>out</b> adapters:
        - persistence details + mapping (Infrastructure Adapters)
        - Presenter (ViewModel Adapters)

3. Extra "Main" (<b>Config</b>) package
    - SpringConfig classes are divided separately for the 'application', 'useCases' and 'infrastructure' layers.

![aggregate model](/docs/img/aggregate-root.jpg)

### TODO: starting building AWS Architecture (first attempt)
![AWS](/docs/img/aws.drawio.png)

## About 'Hotel interaction Application' Project: it is a software system designed to facilitate interactions and improve experiences between guests and hotels.
### The Slogan of this project is 'MAKING GUESTS & HOTELS HAPPY'

![Application intro](/docs/img/app3.png)


#### Special offers on guests
… hotels can send attractive special offers on guests smartphones on demand. Guests can see & book meals or other spa services directly via smartphone from any place. On smartphone guests can communicate with the hotel and other guests to have more fun at their hotel stay.
![Special offers on guests](/docs/img/appDealAccept.png)

### Hotel guests socializing
Guests can get to know other guests in the hotel. And earn bonus points for hotel offers at the same time
![Special offers on guests](/docs/img/appInfo.jpg)

## Architecture details
- According to the "Building Microservices" book by Sam Newman, all microservice should have its own DB (and for example its own view of a central user entity).
- Notifications will be persisted by preconfigured (Jackson serialisation and route) through a central preconfigured 'notification' module, which uses a 'notification'-RabbitMQ-queue for message passing and has its own database.
- When a user update occurs, it will be propagated to all microservices through a 'system' RabbitMQ queue, ensuring that each microservice updates its user-view in its own database.

### "Clean" (Onion) Architecture with java
Based on my another GitHub project:
https://github.com/getsoftware-eu/clean-architecture-hexagonal-my-approach

