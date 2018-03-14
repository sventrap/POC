
Example of Grails secured rest api integrated with postgres.

Postgres setup
1. Install postgres
2. Create new user "restapi" and password "restapi"
3. Create database "restapi"

checkout source code and run the app
./gradle bootrun

Testing:

Login:
1. http://localhost:8080/api/login
body:
{
	"username":"admin",
	"password":"admin"
}

Response:
Copy the bearer token

Insert the record:
1.Add bearer token from above to the authorization header.
2. set Method to POST
3. URL: http://localhost:8080/api/artists
body:
{ 
	"firstName": "John", 
	"lastName": "Smith" 
}

Retrive record:
1. set Method to GET
2. URL: http://localhost:8080/api/artists


Notes:
Grails commands used:
grails create-app restPostgresapi --profile=rest-api
grails s2-quickstart com.postgres.restapi.security AppUser Role
grails create-domain-class com.postgres.restapi.security.AuthenticationToken
grails url-mappings-report

grails create-domain-resource com.postgres.restapi.domain Artist
grails create-restful-controller com.postgres.restapi.Artist
