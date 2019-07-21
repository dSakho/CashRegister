# CHANGELOG

## 7/18/19:
- 

## 7/17/19:
- Updated transaction stage in Application.java file to
    1. Save transactions using Register method.
    2. Pay for purchases using Register method
- Replaced payForPurchase method with payForCart method in Register.java
- Created saveTransaction method in Register.java

## 7/16/19:
- Application now read data source properties from the config file.

## 7/15/19:
- Updated Authorized class to handle edge cases (when the username wasn't correct it would throw a null pointer exception
* Check transaction history by OrderID
- Option 4 should show the transaction total, customer name, time and date, OrderID
- When you select an OrderID you make a database query for every item with that OrderID
- Print all the information about that order
	
## 7/14/19:
- I added a new dependency in the pom file to use the JDBC. Site from mysql documentation (https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-maven.html)
- Needed to run this "SET GLOBAL time_zone = '+3:00';" in Mysql server to correct the timezone exception thrown.
* What are the resource files in a maven app?

