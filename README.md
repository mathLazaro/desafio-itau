# Desafio Itau \- Estudo de Clean Architecture

Descrição
- Projeto de estudo para aplicar princípios de Clean Architecture conforme o requisito em `DESAFIO.md`.
- Implementação em Java com Maven. Uso de `amazoncorretto:25` no Dockerfile.

Estrutura principal
- `pom.xml` \- configuração Maven.
- `mvnw`, `mvnw.cmd` \- wrappers Maven.
- `src/` \- código fonte.
- `target/` \- artefatos gerados (\-jar).
- `Dockerfile` \- imagem multi\-stage com Amazon Corretto 25.
- `docker-compose.yaml` \- composição para executar a aplicação localmente.

Como rodar

1. Usando Maven (desenvolvimento)
- Linux / WSL / macOS:
    - Rodar a aplicação: `./mvnw spring-boot:run`
    - Gerar JAR: `./mvnw -DskipTests package`
    - Testes: `./mvnw test`
- Windows (cmd / PowerShell):
    - Rodar a aplicação: `mvnw.cmd spring-boot:run`
    - Gerar JAR: `mvnw.cmd -DskipTests package`
    - Testes: `mvnw.cmd test`

2. Executando o JAR gerado
- Após build: `java -jar target/desafioitau-0.0.1-SNAPSHOT.jar`
- Ajustar memória/flags via `JAVA_OPTS`, ex.: `JAVA_OPTS="-Xms256m -Xmx512m" java $JAVA_OPTS -jar target/desafioitau-0.0.1-SNAPSHOT.jar`

3. Usando Docker
- Construir imagem: `docker build -t desafioitau .`
- Executar container: `docker run --rm -p 8080:8080 desafioitau`
- Passar opções Java: `docker run --rm -e JAVA_OPTS="-Xms256m -Xmx512m" -p 8080:8080 desafioitau`

4. Usando Docker Compose
- Subir serviço: `docker-compose up --build -d`
- Parar e remover: `docker-compose down`
- O `docker-compose.yaml` já expõe a porta `8080` por padrão.

Configurações relevantes
- Porta padrão: `8080` (ajustar conforme `application.yaml` ou variáveis de ambiente).
- Variável de execução: `JAVA_OPTS` pode ser usada para ajustar memória/flags.

Observações
- Este repositório é um estudo/implementação de aprendizagem e não uma configuração pronta para produção.
- Consulte `DESAFIO.md` para os requisitos do exercício.
- Ajuste versões, portas e configurações conforme necessário para seu ambiente.
