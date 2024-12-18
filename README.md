# Restaurant Reservation System

## Descrição

O projeto **Restaurant Reservation System** é uma aplicação para gerenciamento de reservas e avaliações de restaurantes. Ele permite que os usuários realizem tarefas como cadastrar restaurantes, gerenciar avaliações e buscar informações de forma eficiente. O sistema é desenvolvido em Java com o Spring Boot e segue uma arquitetura modular para facilitar a expansão e manutenção.

---

## Funcionalidades Principais

1. **Cadastro de Restaurantes**:
    - Permite criar, atualizar e buscar informações de restaurantes.

2. **Avaliações de Restaurantes**:
    - Gerenciamento de avaliações por restaurante com notas e comentários dos clientes.

3. **Busca Personalizada**:
    - Pesquisa de restaurantes com filtros como nome, localização e tipo de cozinha.

4. **Testes**:
    - Testes unitários, de integração e de desempenho para garantir a qualidade do sistema.

---

## Tecnologias Utilizadas

- **Linguagem**: Java 17
- **Framework**: Spring Boot
- **Gerenciador de Dependências**: Maven
- **Banco de Dados**: H2 (em memória, para testes e desenvolvimento)
- **Ferramentas de Teste**: JUnit, MockMvc
- **Docker**: Para conteinerização da aplicação
- **Swagger**: Documentação da API

---

## Configuração do Ambiente

1. **Clone o Repositório**:
   ```bash
   git clone <url-do-repositorio>
   cd restaurant-reservation
   ```

2. **Compilação e Execução**:
    - Compile o projeto:
      ```bash
      mvn clean package
      ```
    - Execute a aplicação:
      ```bash
      java -jar target/restaurant-reservation-0.0.1-SNAPSHOT.jar
      ```

3. **Acesse a API**:
    - URL base: `http://localhost:8080`
    - Documentação Swagger: `http://localhost:8080/swagger-ui.html`

---

## Docker

1. **Build da Imagem**:
   ```bash
   docker build -t restaurant-reservation .
   ```

2. **Executar o Container**:
   ```bash
   docker run -p 8080:8080 restaurant-reservation
   ```


## Testes

1. **Unitários e Integração**:
   ```bash
   mvn test
   ```

2. **Cobertura de Testes** (usando JaCoCo):
   ```bash
   mvn clean verify
   ```
    - Relatório gerado em: `target/site/jacoco/index.html`

3. **Testes de Desempenho**:
    - Utilizar ferramentas como JMeter ou k6 para avaliar carga e desempenho.

---



