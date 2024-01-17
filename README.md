# My vision of a Domain-driven Design and Clean Architecture using microservices and AWS

The "Clean" (Layered) Architecture is another way to provide <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units according to their domain and functional roles within the application.
(This approach ensures that the architecture is easy to maintain and extend, as changes to one layer do not affect the others)

### To achieve this vision, I am refactoring my old 'Hotel-interaction' project, in order to encapsulate domain-data (using Domain-driven Design and Clean Architecture principles), which will enable us to create a modular and scalable system that can easily be adapted to future changes and enhancements.

![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)

<b>Domain-layer</b>

To simplify the implementation, we are handling <b>JPA-entities</b> as <b>Domain-entities</b> directly at the <b>Domain-Layer</b>. However, in Clean-architecture,  it is best practice to separate JPA entities from domain entities  and use them only in the <b>Infrastructure-Layer</b> (with JPA repositories).
This separation ensures, that the two types of entities do not depend on each other, but this would require a double declaration of same entity-fields in different layers. While this may seem redundant, it ensures that the domain layer is not tightly coupled to the JPA infrastructure layer, which would make the system less flexible and harder to maintain in the long term.

<b>Application-layer</b> 
- <b>Separation of usecase-logik-methods from technical (low-level) service-help-methods</b>
- Usecases are <b>'interactors'</b> with Domain-Aggregates endpoints: create Obj, findByName...)
  - define own (or use same level-) DTO with <b>lower</b> layers (UserDTO)
    - Or just abstract Generics, instead of own dto: e.g. class UseCase<T: IUser, Z: IUserDTO> 
  - define own (or use same level-) help-IServices ('IUserService') 
    - e.g. 'UserRegisterINTERACTOR' (uses injected IUserService) and makes <b>custom usecases</b> steps based on i-help-infrastructure-methods
    - Problem: used IServices not anemic calls (ServiceImpl.save() dummy call to low-level-Repository.save())


<b>Infrastructure-level</b>
- Implementation of (technical) help-IServices (UserServiceImpl), that was declared in- and will be injected in-INTERACTOR
- Custom implementations for interfaces of upper layer (IServices) : (help-IServices implementation)
- We can update (swap) this custom implementation package with another one, <b>but we don't change upper layer</b> logik!



![Aggregate root](/docs/img/ddd.webp)

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
https://github.com/getsoftware-eu/my-onion-clean-architecture

