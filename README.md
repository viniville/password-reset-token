# POC Token transparente para reset de senha

O objetivo desta POC é implementar a geração de token transparente para reset de senha do usuário, nos casos
em que o usuário tenha esquecido a própria senha.

Esta aplicação cria um banco de dados H2 em memória, que pode ser acessado em http://localhost:8080/h2-console. 

## Executar aplicação

`./mvnw spring-boot:run -Dspring-boot.run.arguments=--createAdminUser=true`

### Default Login admin
Username: admin@email.com

Password: asdfASDF$123

## API's

### List All Active Users

#### cURL Request
``` 
curl --location 'http://localhost:8080/user' \
--header 'Authorization: Basic {base64{user:password}}'
```
#### Response
Status code 200 (Ok)
``` 
[
    {
    "id": "2bc8e641-1eda-4abb-b8a4-3c7e0daab9f2",
    "email": "admin@email.com",
    "fullName": "Administrator of system",
    "birthDate": "1988-03-07"
    }
]
``` 

### Insert User

#### cURL Request
``` 
curl --location 'http://localhost:8080/user' \
--header 'Authorization: Basic {base64{user:password}}' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "teste@gmail.com",
    "password": "ssssdfsdfsdfsdfsdfs&dfsd&fsd22fsfffDD",
    "fullName": "Joao Silva da Silva",
    "birthDate": "07/05/1988"
}'

```
#### Response
Status code 201 (Created)
``` 
{
    "id": "f699f9c5-df9d-4cf7-9041-5e2d04bb1d77",
    "email": "teste@gmail.com",
    "fullName": "Joao Silva da Silva",
    "birthDate": "07/05/1988"
}
``` 

### Deactivate User

#### cURL Request
``` 
curl --location --request DELETE 'http://localhost:8080/user/f699f9c5-df9d-4cf7-9041-5e2d04bb1d77' \
--header 'Authorization: Basic {base64{user:password}}'
```
#### Response
Status code 204 (Created)
