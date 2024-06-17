# Gerenciador de Slots de Tempo
O Gerenciador de Slots de Tempo é uma aplicação responsável por gerenciar a disponibilidade dos profissionais para agendamentos

## Descrição
O Gerenciador de Slots de Tempo é um componente central que oferece funcionalidades para definir, listar e reservar horários de disponibilidade dos profissionais.

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
Navegue até a raiz do projeto e execute o seguinte comando para compilar o projeto utilizando Maven:

```bash
mvn clean package
```

### 2. Construir Imagem Docker:
Antes de iniciar o ambiente com docker-compose, construa a imagem Docker do  Gerenciador de Slots de Tempo:

```bash
docker build -t gerenciador-slot-de-tempo .
```

### 3. Executar com Docker Compose:
Inicie o ambiente utilizando docker-compose, garantindo que o comando seja executado no diretório principal do projeto:

```bash
docker-compose up -d
```

Isso garantirá que todos os serviços necessários sejam iniciados corretamente.

## Documentação do Swagger
O erenciador de Slots de Tempo possui uma documentação do Swagger que descreve os endpoints disponíveis e fornece informações detalhadas sobre como consumir a API.

Para acessar a documentação do Swagger, siga as etapas abaixo:

Verifique se o docker-compose foi corretamente executado.
Abra o navegador e vá para a URL: http://localhost:8080/swagger-ui.html.
Isso abrirá a interface do Swagger, onde você poderá explorar os endpoints, enviar solicitações e visualizar as respostas.

Certifique-se de que o serviço esteja em execução para acessar a documentação do Swagger.

## Funcionalidades Principais
- Listar todos os horários de disponibilidade dos profissionais.
- Obter informações detalhadas de disponibilidade com base no ID.
- Registrar a disponibilidade de um profissional.
- Reservar um horário para um cliente.
- Remover um horário de disponibilidade com base no ID.

## Exemplos de Uso (Curl)
Aqui estão alguns exemplos de como usar as funcionalidades do Gerenciador de Slots de Tempo com curl:

- Registrar a disponibilidade de um profissional:

```bash
curl -X POST "http://localhost:8080/availabilities" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"professionalId\": 1, \"dayOfWeek\": \"MONDAY\", \"startTime\": \"08:00:00\", \"endTime\": \"10:00:00\"}"
```

- Consultar informações de disponibilidade (use o ID obtido na criação):

```bash
curl -X GET "http://localhost:8080/availabilities/{availabilityId}" -H "accept: */*"
```

- Atualizar informações de disponibilidade:

```bash
curl -X PUT "http://localhost:8080/availabilities/{availabilityId}" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"professionalId\": 1, \"dayOfWeek\": \"TUESDAY\", \"startTime\": \"09:00:00\", \"endTime\": \"11:00:00\"}"
```

- Reservar um horário para um cliente:

```bash
curl -X POST "http://localhost:8080/reservations" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"professionalId\": 1, \"startTime\": \"2023-06-14T08:30:00\", \"endTime\": \"2023-06-14T09:30:00\"}"
```

- Deletar um horário de disponibilidade:

```bash
curl -X DELETE "http://localhost:8080/availabilities/{availabilityId}" -H "accept: */*"
```