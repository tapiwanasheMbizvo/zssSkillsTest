# ZSS - Backend Take Home Test

## About the application 
The application exposes a  REST API to add, edit, view and delete books and  their categories.
It also provides end point to purchase a specific book.

## Running the application 
Clone the repository into your machine and cd into the main folder.

## Configuration 

Edit the application.properties file  to specify your connection to a [PostgreSQL]  
```
spring.datasource.url=jdbc:postgresql://{db_ipaddress}:5432/{your_DB}
spring.datasource.username=dbusername
spring.datasource.password=dbpassword
spring.jpa.hibernate.ddl-auto=update

```

## END POINTS 

1.books 

  POST -  takes a JSON input with title, price and category object to persist a book 
 ```
 {

    "title": "Book 1",
    "description": "A fantasy novella",
    "price": 78.96,
    "category":
{
    "id": 1,
    "categoryTitle": "Fiction",
    "books": null
}
}
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
 
2. categories  this endpoint allows  POST, GET, DELETE actions 


POST - Create A category 
```
{
"categoryTitle": "Fiction"
}

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
 It takes an 
 ```
 {
"bookID": 1,
"cardID": "1234560000000001"
}
 #Sends are POST request to purchase book ID 1 , with card ID  1234560000000002
 http://localhost:8080/purchase/
 ```

This returns a JSON Object Specifying if the transaction was authorised or not 

```
{"success":false,"description":"Invalid Transaction"}
```
