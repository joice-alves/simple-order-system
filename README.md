# Sistema Simples de Pedidos 

Este projeto consiste em um sistema completo de gerenciamento de pedidos, clientes, produtos e operadores, composto por uma API robusta no Backend e um painel administrativo dinâmico no Frontend.

O foco principal do desenvolvimento deste desafio foi a **excelência arquitetural do ecossistema Backend**, garantindo resiliência, boas práticas de modelagem de dados e uma camada sólida de segurança.

## Arquitetura do Projeto

O repositório está estruturado como um Monorepo:

- **/backend**: API desenvolvida em Java 21 com Spring Boot, Spring Security (JWT) e persistência em banco de dados relacional PostgreSQL.
- **/frontend**: Aplicação desenvolvida em Angular com componentização moderna baseada em Standalone Components e interface construída com Angular Material.

---

## Tecnologias Utilizadas

### Backend
- **Java 21** & **Spring Boot 4.0.6**
- **Spring Security** & **JWT (JSON Web Tokens)** para autenticação e autorização stateless.
- **Spring Data JPA** & **Hibernate** para mapeamento objeto-relacional.
- **PostgreSQL** como banco de dados de produção.
- **Maven** como gerenciador de dependências.

### Frontend
- **Angular (Componentes Standalone)**
- **Angular Material** para componentes de interface rápidos e responsivos.
- **RxJS** para gerenciamento de requisições assíncronas e fluxos de dados.

---

## Como Executar o Projeto

Para instruções detalhadas de inicialização de cada parte do sistema, acesse os guias específicos:

1. [Guia de Inicialização do Backend & Banco de Dados](./backend/README.md)
2. [Guia de Inicialização do Frontend (Angular)](./frontend/README.md)

---

## Credenciais de Teste

Para realizar o login no painel principal após subir os serviços, utilize o usuário padrão pré-carregado no banco:

- **Login:** `adm`
- **Senha:** `admin123` *(ou a senha que você configurou no seu banco)*