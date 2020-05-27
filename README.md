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
Add the data to request in the following format <BR><BR>	
![Adding UserName and Password](/Capture.PNG)<BR>
	
Add the Client Id and Client Secret in Authorization (use Basic Auth) <BR>
The client ID and Client Secret are Hard coded to :<BR>
	Client ID : testClient <BR>
	Client Secret : my-secret-key<BR>
	
This is how the authorization should be choosen	
![Adding ClientId and Client Secret](/Capture2.PNG)<BR>	


After receiving response use the access_token for accessing the resources of the application

The access_token should be provided under Authorization as Bearer Token

Following are the type of resource URLs available :

Method | Operation | Description | Body |
--- | --- | --- |--- | 
GET | /listCategories | List all the cateogries present | NA |
POST | /addCategory | Add a new category | {"categoryName":,"categoryCode":} |
GET | /categoryItems/{categoryId} | Get all items of Category where {categoryId} represents the desired Category ID | NA |
POST | /addItem | Adds an item to repository | {"name":,"categoryId":,"costPrice":,"sellingPrice":,"quantity":} |
GET | /getCart | Fetches user Cart | NA |
POST | /checkoutCart | Checkout the cart | NA |
POST | /addItemToCart/{itemId} | Adding Item to cart where {itemId}  represents the item id of item to be added | NA |
