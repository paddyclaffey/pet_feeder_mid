
mvn clean install -DskipTests
mvn liquibase:clearCheckSums
mvn liquibase:update