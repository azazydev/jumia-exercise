# Jumia Exercise
## Excercise Description
Create a single page application in Java (Frameworks allowed) that uses the provided 
database (SQLite 3) to list and categorize country phone numbers.
Phone numbers should be categorized by country, state (valid or not valid), country 
code and number.
The page should render a list of all phone numbers available in the DB. It should be 
possible to filter by country and state. Pagination is an extra.

## To Run
```bash
./start.sh
```
or 

```bash
docker build -t jumia-exercise-service jumia-exercise-service/
docker run -p 8080:8080 jumia-exercise-service
docker build -t jumia-exercise-ui jumia-exercise-ui/
docker run -p 80:80 jumia-exercise-ui
```
## To Stop
```bash
./stop.sh
```
or
```bash
docker stop exercise-service
docker stop exercise-ui
docker container rm exercise-ui
docker container rm exercise-service
```
## URLs
Swagger
```
http://localhost:8080/swagger-ui.html
```
UI
```
http://localhost:80
```
