# Etapa de construção
FROM ubuntu:latest AS build

# Atualização dos pacotes e instalação das dependências
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y

# Copiar o código fonte para o container
COPY . .

# Construir a aplicação sem rodar testes
RUN mvn clean package -DskipTests

# Etapa final: imagem leve com JDK
FROM openjdk:17-jdk-slim

# Expor a porta 8080 para a aplicação
EXPOSE 8080

# Copiar o arquivo JAR gerado para o container final
COPY --from=build /target/buka-0.0.1-SNAPSHOT.jar app.jar

# Definir a variável de ambiente para o perfil de produção
ENV SPRING_PROFILES_ACTIVE=prod

# Rodar a aplicação
ENTRYPOINT [ "java", "-jar", "app.jar" ]
