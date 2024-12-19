
# Configuração do Docker para Aplicação

Este projeto utiliza Docker para executar uma aplicação Spring Boot com um banco de dados PostgreSQL.

## Serviços

### App (Aplicação Spring Boot)
- **Imagem**: Construída a partir do `Dockerfile`.
- **Portas expostas**: `8080:8080`.
- **Variáveis de ambiente**:
  - `SPRING_DATASOURCE_URL`: URL do banco de dados PostgreSQL.
  - `SPRING_DATASOURCE_USERNAME`: Nome de usuário para o banco.
  - `SPRING_DATASOURCE_PASSWORD`: Senha do banco.
  - `SPRING_PROFILES_ACTIVE`: Perfil de produção (`prod`).
- **Dependência**: `db` (PostgreSQL).

### Banco de Dados (PostgreSQL)
- **Imagem**: `postgres:latest`.
- **Nome do container**: `postgres-db`.
- **Variáveis de ambiente**:
  - `POSTGRES_DB`: Nome do banco de dados (`tfc`).
  - `POSTGRES_USER`: Nome de usuário para o PostgreSQL.
  - `POSTGRES_PASSWORD`: Senha do banco.
- **Portas expostas**: `5432:5432`.
- **Volume**: `postgres_data` (persistência de dados).

## Dockerfile

### Etapa de Construção
- **Base**: `ubuntu:latest`.
- Instalação de dependências:
  - OpenJDK 17
  - Maven
- **Comando**:
  ```bash
  mvn clean package -DskipTests
