FROM eclipse-temurin:17-jdk-jammy AS builder


WORKDIR /mvn


COPY ./pom.xml ./pom.xml
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn
RUN ./mvnw package -DskipTests


COPY ./src ./src
RUN ./mvnw package


FROM tomcat:10.1.13-jre17-temurin-jammy


RUN groupadd -r reviews \
 && useradd -r -g reviews reviews \
 && chown reviews:reviews -R /usr/local/tomcat/*


USER reviews:reviews

COPY --from=builder /mvn/target/reviews.war /usr/local/tomcat/webapps/


EXPOSE 8080
