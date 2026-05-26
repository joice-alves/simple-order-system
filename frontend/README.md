# Frontend - Painel Principal Sistema Simples de Pedidos

Este é o módulo de interface do sistema, desenvolvido em Angular para simular um painel de controle administrativo dinâmico e responsivo para o gerenciamento de pedidos.

---

## Tecnologias e Recursos
- **Angular 17+**
- **Componentes Standalone** (Arquitetura moderna sem NgModules globais)
- **Injeção de Dependências** via função `inject()` nativa
- **Angular Material** (Tabs, Toolbar, Cards, Tables e Icons)
- **RxJS** para controle de fluxos assíncronos

---

## Pré-requisitos
Antes de iniciar, certifique-se de ter instalado em sua máquina:
- **Node.js** (Versão 18 ou superior)
- **Angular CLI** instalado globalmente (`npm install -g @angular/cli`)

---

## Como Executar o Frontend

1. Entre na pasta do frontend:
```bash
cd frontend
```
2. Instale todas os pacotes e dependências necessárias:
```bash
npm install
```
3. Inicialize o servidor de desenvolvimento local:
```bash
ng serve
```
4. Abra o seu navegador e acesse a aplicação em: http://localhost:4200

## Estrutura do Projeto e Componentização

A aplicação foi estruturada focando em boas práticas de desacoplamento, utilizando Standalone Components. O fluxo principal está concentrado na Dashboard de listagem:

+ OrderListComponent: Componente centralizador que utiliza o <mat-tab-group> do Angular Material para alternar a visualização de dados entre as 4 principais entidades do negócio em uma única página, sem necessidade de recarregamento (SPA).
+ Services: Camada de serviços (OrderService, AuthService) isolada e preparada com cabeçalhos HTTP dinâmicos para injetar o Token JWT coletado no login.

## Status Atual e Próximos Passos (Atenção!)

Visando priorizar o prazo estipulado para a entrega do desafio e focar no desenvolvimento robusto da API de Backend (mecanismos de persistência no PostgreSQL via Docker Compose, relacionamentos do Hibernate e segurança stateless via Spring Security), a interface frontend encontra-se em estágio WIP (Work in Progress).

### O que está funcional:
- Layout estruturado e responsivo com Angular Material.
- Fluxo de autenticação, captura de credenciais e armazenamento seguro do Token JWT no localStorage.
- Estruturação visual das abas de navegação (`<mat-tab-group>`) e tabelas preparadas para a exibição das quatro entidades de negócio.

### O que falta para finalizar o sistema:
- **Integração dos Services:** Conectar os métodos de ciclo de vida dos componentes standalone aos Services do Angular para disparar o consumo dos endpoints da API.
- **Mapeamento de DataSources:** Vincular o retorno das requisições HTTP aos componentes `<table mat-table>` utilizando a classe `MatTableDataSource` para renderização dinâmica.
- **Formulários de CRUD:** Implementação das telas e modais de inserção/edição para Clientes, Produtos, Usuários e Pedidos.
