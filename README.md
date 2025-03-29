# My Vision of Clean Architecture and Domain-driven Design using microservices and AWS

In this project, I use a <b>Hexagon</b> as a boundary between an application and the outside world. 

Inside the 'hexagon', I use a mix of simplified <b>Domain-Driven Design</b> (DDD) with <b>Clean</b>-Architecture (The 'infrastructure' and 'use case' logic is grouped into separate layers).

DDD helps maintain boundaries between domains.

The "Clean" (Layered) Architecture is another way to provide <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units according to their domain and functional roles within the application.
(This approach ensures that the architecture is easy to maintain and extend, as changes to one layer do not affect the others)

### To achieve this vision, I am refactoring my old 'Hotel-interaction' project, in order to encapsulate domain-data (using Domain-driven Design and Clean Architecture principles as Onion and Hexagonal), which will enable us to create a modular and scalable system that can easily be adapted to future changes and enhancements.


## "Hotelico" Cloud (AWS) Architecture
![AWS](/docs/img/aws.drawio.png)

![DDD in Hexagon Architecture](/docs/img/adapterMultiDomain.JPG)
![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)

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
           - e.g. 'CheckinUseCaseImpl' (uses injected IAsynchronMessagingService) and implements <b>custom usecases</b> steps based on i-help-infrastructure-methods
           - Problem: used IServices anemic calls (ServiceImpl.save() dummy call to low-level-Repository.save())
   - <b>Port</b> layer: interfaces for adapters (public boundaries)

2. <b>Adapter</b> layer - Implementation of port-IServices (UserServiceImpl), that was declared in- and will be injected in-INTERACTOR

     - <b>in</b> adapters:
        - specific user request (Controller Adapters)
    - <b>out</b> adapters:
        - persistence details + mapping (Infrastructure Adapters)
        - Presenter (ViewModel Adapters)
      
          - In fact lowest level: only Repository(Service-translator: Dto<--to-->Repository), Controller(Rest-Api), API-Handler-Exceptions, AWS(data actions) 


3. Extra "Main" (<b>Config</b>) package
    - SpringConfig classes are divided separately for the 'application', 'useCases' and 'infrastructure' layers.


### Event-driven interaction (with Kafka)

#### In our domain-driven system, I use for Kafka-topics following naming convention:

"aggregate_entity_action_event"

##### Example Topics:
"hotel_offers_requested_event"

"chat_message_deleted_event" 

(This topic name ensures that every subscribing domain can easily interpret and process events, maintaining consistency and clarity across the system.)

![Event Sourcing](/docs/img/eventSourcing.png)

The key for a Kafka-message is the 'receiverId' (to improve Kafka-groupId partitioning).

![Aggregate root](/docs/img/ddd.webp)

![event-driven](/docs/img/cca-event-driven.JPG)


## Simplified Domain-driven Design:
### Interaction via (how to couple bounded contexts):
#### - shared domain-events (sharing updates between contexts)
#### - or application-service (that orchestrate ddd contexts)
### Every bounded context stores its entities in its own DB schema (+ own persistence adapters)
#### - Example @Table(schema = "userschema")

[//]: # (![aggregate model]&#40;/docs/img/aggregate-root.jpg&#41;)



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
- According to the "Building Microservices" book by Sam Newman, all microservice should have its own DB (and for example its own projection-view of a central user entity).
- Messaging will be persisted by preconfigured (Jackson serialisation and route) through a central preconfigured 'messaging' module, which uses a 'notification'-Kafka-queue for message passing and has its own database.
- When a user update occurs, it will be propagated to other microservices through a 'DDD-compliant-named' Kafka queue, ensuring that each microservice updates its user-view in its own database.

## Used Projects as a basis:

### "Clean" Architecture with java
Based on my another GitHub project:
https://github.com/getsoftware-eu/clean-architecture-hexagonal-my-approach

Practical Event-Driven Microservices Architecture
https://www.springerprofessional.de/en/practical-event-driven-microservices-architecture/19852048

DDD video
https://drive.google.com/drive/u/0/folders/1Esy5fINcub-vABnkysnP1u-OyKktbl5T?sort=13&direction=a