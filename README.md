# InventorySystem
The project demonstrates a simple inventory system in which users can login,view categories and items related to category,add items to cart and checkout cart.
It is made RESTFULL to easily bind with any kind of user-end.

Implementation 
---
The project is implemented using Spring Boot framework in Java and Spring Data is used JPA with Hibernate ORM working under the hood.
The Database used is MySQL although, the DB implementation can be changed easily by configurations.

Security
---
The project is secured with OAuth2 

## Installation

Checkout the project from GIT in your preffered IDE.<br>
Go to InventorySystemApplication.java and run the file as simple java application.


## Working

To start working with the application you first need to create a new user using the following URL<br>
<APP_BASE_URL>/api/v1/user/signUp <BR>
Provide the following JSON as body in the request

```JSON
{
	"userName":[USER_NAME],
	"password":[PASSWORD],
	"firstName":[FIRSTNAME],
	"lastName":[LASTNAME]
}
```

After user creation proceed for login. Use the following URL for logging into application
<APP_BASE_URL>/oauth/token <BR>
![Alt Text](/Capture.PNG)



