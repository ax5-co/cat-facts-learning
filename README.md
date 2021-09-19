# cat-facts-learning
A project I created to practice MapStruct, Retrofit & Swagger. 

It currently supports 2 apis as in the Apicontroller.java + the swagger-related apis.

The main 4 apis are listed in the urls.txt file, but here they are:


Application urls:

To retrieve all cat breeds (pagination enabled): 
/api/breeds?page=2&limit=5

To retrieve all cat breeds searched by a term 
(limit Path Variable limits how many breeds to be displayed, no pagination):
/api/breeds/search?term=eg&limit=1

openApi definition (SwaggerUI) by Swagger:
/swagger-ui.html

OpenApi docs:
/v3/api-docs
