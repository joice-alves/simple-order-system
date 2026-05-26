# Backend - API Sistema Simples de Pedidos

Esta é a API RESTful do sistema, desenvolvida com foco em alta resiliência, boas práticas de modelagem de dados e segurança robusta baseada em tokens de autenticação stateless.

---

## Tecnologias e Recursos
- **Java 21** & **Spring Boot 3**
- **Spring Security** com autenticação **JWT (JSON Web Tokens)**
- **Spring Data JPA** & **Hibernate**
- **PostgreSQL 15** (via Docker Compose)
- **Maven** (Gerenciador de dependências)

---

## Pré-requisitos
Antes de iniciar, certifique-se de ter instalado:
- **Java JDK 17** ou superior
- **Docker** e **Docker Compose** (ou Docker Desktop)
- **Postman** (para testes de endpoints)

---

## Como Executar o Backend

Siga os passos abaixo na ordem indicada para garantir o funcionamento correto:

### 1. Inicializar o Banco de Dados (Docker Compose)
A infraestrutura do banco de dados está automatizada. Certifique-se de estar na pasta raiz do backend (onde está o arquivo `docker-compose.yml`) e execute o comando abaixo para subir o container do PostgreSQL em segundo plano:

```bash
docker compose up -d
````
**Informações do Banco Criado:**

Container Name: `order-system-db`
Database: `ordersystemdb`
User: `jma_admin`
Porta: `5432`

### 2. Carga Inicial de Dados (Data Seeding)
A aplicação conta com um mecanismo automatizado via Hibernate (import.sql). Ao inicializar a API em modo de desenvolvimento, a tabela de usuários será criada e populada automaticamente com um operador administrativo padrão criptografado em BCrypt.

### 3. Executar a Aplicação Spring Boot
Com o container do banco rodando, execute o comando abaixo na pasta do backend para subir o servidor Java:

**No Linux ou macOS:**
```bash
./mvnw spring-boot:run
```


**No Windows:**
```bash
mvnw.cmd spring-boot:run
```
A API estará rodando com sucesso em: http://localhost:8080

---

## API Endpoints
| Método |      Endpoint      | Descrição                             |
|:-------|:------------------:|:--------------------------------------|
| POST   |    /auth/login     | Gera um token e autoriza autenticação |
| POST   |   /api/usuarios    | Cria um novo usuário                  |
| GET    |   /api/usuarios    | Retorna todos os usuários             |
| GET    | api/usuarios/{id}  | Retorna usuário por ID                |
| PUT    |    api/usuarios    | Atualiza usuários                     |
| DELETE | /api/usuarios/{id} | Deleta usuário por ID                 |
| POST   |    /api/cliente    | Cria um novo cliente                  |
| GET    |    /api/cliente    | Retorna todos os clientes             |
| GET    |  api/cliente/{id}  | Retorna cliente por ID                |
| PUT    |    api/cliente     | Atualiza usuários                     |
| DELETE | /api/cliente/{id}  | Deleta cliente por ID                 |
| POST   |   /api/produtos    | Cria um novo produto                  |
| GET    |   /api/produtos    | Retorna todos os produtos             |
| GET    | api/produtos/{id}  | Retorna produto por ID                |
| PUT    |    api/produtos    | Atualiza produtos                     |
| DELETE | /api/produtos/{id} | Deleta produto por ID                 |
| POST   |    /api/pedidos    | Cria um novo pedido                   |
| GET    |    /api/pedidos    | Retorna todos os pedidos              |
| GET    |  api/pedidos/{id}  | Retorna pedido por ID                 |
| PUT    |    api/pedidos     | Atualiza pedido                       |
| DELETE | /api/pedidos/{id}  | Deleta pedidos por ID                 |

---

## Autenticação e Segurança

Com exceção do endpoint de login, todas as rotas da API estão protegidas pelo Spring Security e exigem o cabeçalho HTTP de autorização.

### Credenciais de Teste (Carregadas via import.sql)
- Mecanismo de Autenticação: POST /auth/login
- Usuário: adm
- Senha: admin123

**Exemplo de Uso nos Endpoints Protegidos:**
Para testar os demais endpoints (ex: GET /api/usuarios), capture o token string devolvido no login e insira-o na requisição como um Bearer Token:
`Authorization: Bearer <seu_token_jwt>`