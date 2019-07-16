# CHANGELOG
 
## 7/15/19:
- Updated Authorized class to handle edge cases (when the username wasn't correct it would throw a null pointer exception
* Check transaction history by OrderID
	--- Option 4 should show the transaction total, customer name, time and date, OrderID
	--- When you select an OrderID you make a database query for every item with that OrderID
	--- Print all the information about that order
	
## 7/14/19:
- I added a new dependency in the pom file to use the JDBC. Site from mysql documentation (https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing-maven.html)
- Needed to run this "SET GLOBAL time_zone = '+3:00';" in Mysql server to correct the timezone exception thrown.
* What are the resource files in a maven app?

