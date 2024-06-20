# Gerenciador de Slots de Tempo
O Gerenciador de Slots de Tempo é uma aplicação responsável por gerenciar a disponibilidade dos profissionais para agendamentos.

## Descrição
O Gerenciador de Slots de Tempo é um componente central que oferece funcionalidades para definir, listar e reservar horários de disponibilidade dos profissionais. Ele ajuda a organizar e otimizar a agenda dos profissionais, facilitando o processo de marcação de compromissos.

## Arquitetura
O serviço é projetado como um microserviço independente e segue os princípios de arquitetura de microsserviços.
Ele é desenvolvido usando tecnologias como Spring Boot, Java 17 e banco de dados H2 para armazenamento dos dados de disponibilidade e reservas.

## Integrações
O Gerenciador de Slots de Tempo pode se integrar com outros serviços de agendamento e gerenciamento de profissionais dentro do ecossistema de microsserviços da aplicação.

## Requisitos do Sistema
Para executar o Gerenciador de Slots de Tempo, verifique se você possui os seguintes requisitos instalados:

- Java 17: [Instalar Java 17](https://www.oracle.com/java/technologies/downloads/)
- Maven: [Instalar Maven](https://maven.apache.org/install.html)
- Docker: [Instalar Docker](https://docs.docker.com/get-docker/)

Certifique-se de que o Java 17 e o Maven estejam configurados corretamente em seu ambiente.

## Compilação e Execução

### 1. Compilar o Projeto:
Para compilar o projeto utilizando Maven, navegue até a raiz do projeto e execute:

```bash
mvn clean package
```
Este comando irá compilar o código fonte, executar os testes e gerar o arquivo JAR executável na pasta target.

### 2. Construir e Executar com Docker:

#### 2.1 Construir Imagem Docker
Antes de iniciar o ambiente com docker-compose, construa a imagem Docker do Gerenciador de Slots de Tempo. Certifique-se de estar no diretório principal do projeto onde está localizado o Dockerfile:

```bash
docker build -t gerenciador-slots-de-tempo .
```

#### 2.2 Executar com Docker Compose
Utilize docker-compose para iniciar o ambiente completo:

```bash
docker-compose up -d
```
Este comando iniciará todos os serviços necessários definidos no arquivo docker-compose.yml em segundo plano (-d).

### 3. Executar Localmente com Maven:
Para iniciar a aplicação localmente utilizando Maven:

```bash
mvn spring-boot:run
```

### Acessar a Aplicação
Após iniciar a aplicação, você pode acessar os endpoints e a documentação Swagger. Certifique-se de que todos os serviços estão funcionando corretamente antes de prosseguir.

## Documentação do Swagger
O Gerenciador de Slots de Tempo possui uma documentação do Swagger que descreve os endpoints disponíveis e fornece informações detalhadas sobre como consumir a API. Swagger é uma ferramenta útil para visualizar e interagir com a API sem a necessidade de escrever código manualmente

Para acessar a documentação do Swagger, siga as etapas abaixo:

1. Verifique se o docker-compose foi corretamente executado. 
2. Abra o navegador e vá para a URL: http://localhost:8080/swagger-ui.html.

Isso abrirá a interface do Swagger, onde você poderá explorar os endpoints, enviar solicitações e visualizar as respostas.

* Certifique-se de que o serviço esteja em execução para acessar a documentação do Swagger.

## Funcionalidades Principais
- Listar todos os horários de disponibilidade dos profissionais.
- Obter informações detalhadas de disponibilidade com base no ID.
- Registrar a disponibilidade de um profissional.
- Remover um horário de disponibilidade com base no ID.
- Reservar um horário para um cliente.
- Obter todas as reservas.

## Exemplos de Uso (Curl)
Aqui estão alguns exemplos de como usar as funcionalidades do Gerenciador de Slots de Tempo com curl:

- Registrar a disponibilidade de um profissional:

```bash
curl -X POST "http://localhost:8080/api/availabilities" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"professionalId\": "827c10f9-412f-4203-8d1b-49a7187cc7b9", \"dayOfWeek\": \"MONDAY\", \"startTime\": \"08:00:00\", \"endTime\": \"10:00:00\"}"
```

- Consultar informações de disponibilidade (substitua {availabilityId} pelo ID obtido na criação):

```bash
curl -X GET "http://localhost:8080/api/availabilities/{availabilityId}" -H "accept: */*"
```

- Atualizar informações de disponibilidade (substitua {availabilityId} pelo ID obtido na criação):

```bash
curl -X PUT "http://localhost:8080/api/availabilities/{availabilityId}" -H "Content-Type: application/json" -d '{ "professionalId": "827c10f9-412f-4203-8d1b-49a7187cc7b9", "dayOfWeek": "MONDAY", "startTime": "09:00:00", "endTime": "11:00:00"}'
```

- Deletar um horário de disponibilidade (substitua {availabilityId} pelo ID obtido na criação):

```bash
curl -X DELETE "http://localhost:8080/api/availabilities/{availabilityId}" -H "accept: */*"
```

- Reservar um horário para um cliente 

```bash
curl -X POST "http://localhost:8080/api/reservations" -H "accept: */*" -H "Content-Type: application/json" -d "{ "professionalId": "827c10f9-412f-4203-8d1b-49a7187cc7b9", "startTime": "08:30:00", "endTime": "09:30:00"}"
```

- Obter todas as reservas:

```bash
curl -X GET "http://localhost:8080/api/reservations" -H "accept: */*"
```