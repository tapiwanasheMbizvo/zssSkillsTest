# ZSS - Backend Take Home Test

## About the application 
The application exposes a  REST API to add, eddit, view and delere books and  their categories.
It also provides end point to purchase a specific book.

## Running the application 
Clone the repository into your machine and cd into the main folder.

## Configuration 

Edit the application.properties file  to specify your connection to a [PostgreSQL]  database.

## END POINTS 

1.books 

  POST - mehtod takes a JSON input with title, price and category object to persist a book 
 ```
 /books
 ```
 GET - View all books 
 ```
 /books
 ```
 GET - View one Book
 ```
 /books/{id}
 ```
 DELETE - Delete a book 
 
 ```
 /books/{id}
 ```
 
2. categories  thie end pint allows  POST, GET, DELTE actions 

```
 /categories
 ```
 GET - View all Categories 
 ```
 /categories
 ```
 GET - View one Category
 ```
 /categories/{id}
 ```
 DELETE - Delete a Category 
 
 ```
 /categories/{id}
 ```
 3. Purchase 
 This endpoint allows clients to make purchase requests to the books by specifying a book ID and CARD ID 
 
 ```
 #Sends are GET request to purchase book ID 1 , with card ID  1234560000000002
 http://localhost:8080/purchase/1/1234560000000002
 ```

This returns a JSON Object Specifying if the transaction was authorised or not 

```
{"success":false,"description":"Invalid Transaction"}
```
