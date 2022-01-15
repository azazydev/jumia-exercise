docker build -t jumia-exercise-service jumia-exercise-service/
docker run -d --name exercise-service -p 8080:8080 jumia-exercise-service
docker build -t jumia-exercise-ui jumia-exercise-ui/
docker run -d --name exercise-ui -p 80:80 jumia-exercise-ui
