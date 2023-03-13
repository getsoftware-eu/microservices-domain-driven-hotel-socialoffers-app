# My vision of a Domain-driven Design and Clean Architecture implementation with microservices and AWS

"Clean" (Layered) Architecture is another way to provide for <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units by their domain and functional role within the application.

### It's a refactoring of the old 'Hotel-interaction' project: an attempt to encapsulate domain-data (with Domain-driven Design and Clean Architecture).

![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)

For simplicity, <b>Jpa-entities</b> will be handled as <b>Domain-entities</b> directly at the <b>Domain-Layer</b>, but actually in Clean-architecture Jpa-Entities should be separated from Domain-entities and should be used only in <b>Infrastructure-Layer</b> (with Jpa Repository).
Then they would not depend on each other, but this would require a double declaration of same entity-fields in different layers.

![Aggregate root](/docs/img/ddd.webp)

## About 'Hotel interaction Application'
### 'MAKING GUESTS & HOTELS HAPPY'

![Application intro](/docs/img/app3.png)


#### Special offers on guests
… hotels can send attractive special offers on guests smartphones on demand. Guests can see & book meals or other spa services directly via smartphone from any place. On smartphone guests can communicate with the hotel and other guests to have more fun at their hotel stay.
![Special offers on guests](/docs/img/appDealAccept.png)

### Hotel guests socializing
Guests can get to know other guests in the hotel. And earn bonus points for hotel offers at the same time
![Special offers on guests](/docs/img/appInfo.jpg)

### TODO: starting building AWS Architecture (first attempt) 
![AWS](/docs/img/aws.drawio.png)

### "Clean" (Onion) Architecture with java
Based on my another Github project:
https://github.com/getsoftware-eu/my-onion-clean-architecture