# Gestão Resíduos Coleta

Um sistema de gerenciamento de coleta de resíduos, desenvolvido com Spring Boot e Oracle Database usando Docker.

## Pré-requisitos

- **Java 18**
- **Maven**
- **Docker e Docker Compose**

## Configuração

### 1. Banco de Dados (Docker)

Configure o arquivo `docker-compose.yml`:

```yaml
version: '3.8'
services:
  oracle:
    image: container-registry.oracle.com/database/free:latest
    environment:
      - ORACLE_PWD=your_password
    ports:
      - "1521:1521"
      - "5500:5500"
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:5500"]
      interval: 30s
      timeout: 10s
      retries: 5

  api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=dev
    depends_on:
      - oracle
```
# Instruções para Rodar o Projeto

## 1. Suba os Serviços

Substitua `your_password` pela senha desejada.

Suba os serviços:

```bash
docker-compose up -d
```

# Configuração
No arquivo src/main/resources/application.properties, configure:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/xe
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.Oracle12cDialect
```
Substitua your_username e your_password com as credenciais configuradas.

# Executando a Aplicação
Compile e rode a aplicação:

```
mvn clean install
mvn spring-boot:run
```
- A aplicação será iniciada na porta 8080.

Comandos Úteis
Parar os contêineres: `docker-compose down`

Ver logs da API: `docker-compose logs -f api`

- Notas

### Certifique-se de que o Docker está executando antes de iniciar o projeto.

A imagem do Oracle pode consumir muitos recursos. Verifique se seu sistema tem memória suficiente, a imagem possui 9GB.