# ws-rasmoo-plus

REST API based on Spring Boot to serve the screens of the Rasmoo Plus project, a project inspired by the Netflix layout.

## Layout
Available in 

https://www.figma.com/file/VGuOtA84zWB4Hnlw2xsWIN/rasmoo-plus---class?type=design&node-id=0%3A1&mode=design&t=1QndlzOj5Klfpi49-1

<h3> Running the Project </h3>

- Install docker and docker-compose if you don't have it
- Clone the git repository and checkout your branch to 'atualizacao-springboot3' if you want to launchit in spring
boot 3, else keep it on master branch
- Go do docker-compose.yml file in root project and launch mysql and redis running 'docker-compose up -d' command
- Check if mysql and redis are up, running 'docker ps' command
- Now, launch the ws-rasmoo-plus running WsRasmooPlusApplication main class
- Obs. You can deploy the API by running the 'mvn clean install' command at the root of the project, then navigating to the 'target' folder and running the 'java -jar ws-rasmoo-plus-1.0.0.jar' command, or by running the 'mvn spring-boot:run' command also at the root of the project
- You can check if the api is up at http://localhost:8082/ws-rasplus/v1/v3/api

<h3></h3> 
