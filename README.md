# spring-boot-restapi-unit-test
# What you'll need
JDK 8+ or OpenJDK 8+
Maven 3+

# Stack
Spring Boot
MockMVC

# Run
mvn clean test spring-boot:run

# Integration test with cURL
# Create a new stock

curl -H "Content-Type: application/json" -X POST -d '{"name":"Stock 1","price":"1"}' http://localhost:8080/api/v1/stocks
# Get all stocks

curl http://localhost:8080/api/v1/stocks
Get stock id 1

curl http://localhost:8080/api/v1/stocks/1
# Update price of stock 1

curl -i -X PATCH -H "Content-Type:application/json" -d "{\"name\" : \"Stock 1 Updated\"}" http://localhost:8080/api/v1/stocks/1
