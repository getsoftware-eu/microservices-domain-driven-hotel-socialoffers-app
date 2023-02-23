# Domain-driven Design - my Java implementation

"Clean" (Layered) Architecture is another way to provide for <b>separation of concerns</b>, <b>encapsulation</b> and <b>decoupling</b>, by grouping code units by their domain and functional role within the application.

### It's a refactoring of the old 'Hotel-interaction' project: an attempt to encapsulate domain-data (with Domain-driven Design and Onion Architecture).

![Agregates is only one entry to domain entities](/docs/img/diagramm1.png)

## Hotel interaction Application
### 'MAKING GUESTS & HOTELS HAPPY'

![Application intro](/docs/img/app3.png)


#### Special offers on guests
… hotels can send attractive special offers on guests smartphones on demand. Guests can see & book meals or other spa services directly via smartphone from any place. On smartphone guests can communicate with the hotel and other guests to have more fun at their hotel stay.
![Special offers on guests](/docs/img/appDealAccept.png)

### Hotel guests socializing
Guests can get to know other guests in the hotel. And earn bonus points for hotel offers at the same time
![Special offers on guests](/docs/img/appInfo.jpg)



### Onion "Clean" Architecture with java
Based on my another Github project:
https://github.com/getsoftware-eu/my-onion-clean-architecture