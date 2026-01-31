# =========================
# Build stage
# =========================
FROM amazoncorretto:25 AS build

RUN yum -y install tar gzip shadow-utils && \
    curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.9.7/binaries/apache-maven-3.9.7-bin.tar.gz \
      -o /tmp/maven.tar.gz && \
    mkdir -p /opt/maven && \
    tar -xzf /tmp/maven.tar.gz -C /opt/maven --strip-components=1 && \
    rm -rf /tmp/maven.tar.gz && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

WORKDIR /workspace
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn -B package -DskipTests

# =========================
# Runtime stage
# =========================
FROM amazoncorretto:25

RUN yum -y install shadow-utils && useradd -m appuser && yum clean all

WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
RUN chown appuser:appuser /app/app.jar

USER appuser
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
