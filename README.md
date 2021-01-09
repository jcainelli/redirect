## API - Encurtador de URL
Encurta url em url encurtadas com redirecionamento

#### Dependências:
* Maven
* Spring Boot
* Derby 
* Hibernate

#### Compilar e executar no diretorio do arquivo pom.xml
mvn spring-boot:run

#### Compilar: 
mvn clean package	

#### Gerar Imagem Docker:
mvn dockerfile:build

#### Visualizar imagem no Docker:
docker image list

### Executar no Container Localmente
docker run -p 8080:8080 jeancainelli/redirect --spring.profiles.active=dev

### Documentação Swagger:
http://localhost:8080/doc/swagger-ui.html

#### Parâmetros:
* short.url.valid.time = Tempo em minutos de expiração de uma Url encurtada. Valor Default = 30