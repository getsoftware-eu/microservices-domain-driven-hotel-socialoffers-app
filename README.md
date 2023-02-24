# My vision of a Domain-driven Design and Clean Architecture implementation with microservices and K8s

"Clean" (Layered) Architecture is another way to provide for <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units by their domain and functional role within the application.

### It's a refactoring of the old 'Hotel-interaction' project: an attempt to encapsulate domain-data (with Domain-driven Design and Onion Architecture).

![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)
![Aggregate root](/docs/img/ddd.webp)

For simplicity, jpa-entities will be handled as domain-entities directly at the Domain-Layer, but actually they should be separated from domain-entities and be used only in infrastructure layer (with jpa repository).
Then they would not depend on each other, but this would require a double declaration of all fields.

## About 'Hotel interaction Application'
### 'MAKING GUESTS & HOTELS HAPPY'

![Application intro](/docs/img/app3.png)


#### Special offers on guests
… hotels can send attractive special offers on guests smartphones on demand. Guests can see & book meals or other spa services directly via smartphone from any place. On smartphone guests can communicate with the hotel and other guests to have more fun at their hotel stay.
![Special offers on guests](/docs/img/appDealAccept.png)

### Hotel guests socializing
Guests can get to know other guests in the hotel. And earn bonus points for hotel offers at the same time
![Special offers on guests](/docs/img/appInfo.jpg)



### "Clean" (Onion) Architecture with java
Based on my another Github project:
https://github.com/getsoftware-eu/my-onion-clean-architecture