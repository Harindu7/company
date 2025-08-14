# Run stage
FROM container-registry.oracle.com/java/jdk:21
ADD target/company_repo.jar company_repo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "company_repo.jar"]