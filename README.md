<div align="center">
  <code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201470-f6d56780-adec-11eb-8f7c-e70e376cfd07.png" alt="Spring" title="Spring"/></code>
  <code><img width="50" src="https://user-images.githubusercontent.com/25181517/182884177-d48a8579-2cd0-447a-b9a6-ffc7cb02560e.png" alt="mongoDB" title="mongoDB"/></code>
  <code><img width="50" src="https://user-images.githubusercontent.com/25181517/182884894-d3fa6ee0-f2b4-4960-9961-64740f533f2a.png" alt="redis" title="redis"/></code>	
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207330-263ba280-adf4-11eb-9b97-0ac5b40bc3be.png" alt="Docker" title="Docker"/></code>
  <code><img width="50" src="https://user-images.githubusercontent.com/25181517/117533873-484d4480-afef-11eb-9fad-67c8605e3592.png" alt="JUnit" title="JUnit"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183892181-ad32b69e-3603-418c-b8e7-99e976c2a784.png" alt="mocikto" title="mocikto"/></code>
  <code><img width="50" src="https://user-images.githubusercontent.com/25181517/184097317-690eea12-3a26-4f7c-8521-729ebbbb3f98.png" alt="Testcontainers" title="Testcontainers"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192107854-765620d7-f909-4953-a6da-36e1ef69eea6.png" alt="HTTP" title="HTTP"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" alt="REST" title="REST"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ" title="IntelliJ"/></code>
	<code><img width="50" src="https://cdn.brighttalk.com/ams/california/images/channel/19357/image_840418.png" alt="Auth0" title="Auth0"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/190229463-87fa862f-ccf0-48da-8023-940d287df610.png" alt="Lombok" title="Lombok"/></code>
</div>    

This Application simulates lotto game. User gives input numbers and receive a ticket. Then every Saturday at 12:00 application generates winning numbers and at 12:05 user is able to check result.

Generating numbers is done by app using scheduler. To use application user has to register, login and fetch JWT token

To give input numbers use endpoint:
- POST /inputNumbers

To check result use endpoint:
- GET /results/{id}

All functionality are unit and integration tested.

# Core
- Java  
- Spring  
- MongoDb
- Redis
- Spring Security
- Scheduler
- Docker
- JWT
# Testing:
- JUnit  
- AssertJ
- Mockito
- Testcontainers
- Wiremock
- MockMvc

# Architecture
![image](https://github.com/ppirog/Lotto/assets/126290295/480a195f-9cac-4015-ac78-4d88b6918c74)

# Postman
![image](https://github.com/ppirog/Lotto/assets/126290295/91f110e1-4f7d-4676-8e13-7cb380e7a59c)
![image](https://github.com/ppirog/Lotto/assets/126290295/5a686ed9-ade6-4bcf-bae5-c9b76f089329)
![image](https://github.com/ppirog/Lotto/assets/126290295/de78b079-911e-4277-90bc-26e05b7f3527)
![image](https://github.com/ppirog/Lotto/assets/126290295/f83ea9fd-b598-44b1-acec-9a202d71ac4c)
![image](https://github.com/ppirog/Lotto/assets/126290295/e9d01725-2e89-4e5f-b112-d4644c101799)
![image](https://github.com/ppirog/Lotto/assets/126290295/9d18e26d-d755-47e2-af79-543150c7e2a1)

##### To start app go to go to file docker-compose.yml first and launch it









  
