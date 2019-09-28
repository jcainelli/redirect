## API - Encurtador de URL
Encurta url em url encurtadas com redirecionamento

#### Dependências:
* Maven
* Spring Boot
* Derby 
* Hibernate

#### Compilar: 
mvn clean package	

#### Gerar Imagem Docker:
mvn dockerfile:build

#### Visualizar imagem no Docker:
docker image list

### Executar Container Localmente
docker run -p 8080:8080 jcainelli/redirect --spring.profiles.active=dev

### Documentação Swagger:
http://localhost:8080/doc/swagger-ui.html

#### Parâmetros:
* short.url.valid.time = Tempo em minutos de expiração de uma Url encurtada.