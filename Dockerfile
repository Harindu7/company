FROM eclipse-temurin:21-jre
ADD target/company_repo.jar company_repo.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "company_repo.jar"]