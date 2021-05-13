# JDBC: Java Database Connectivity

Java API that can access any kind of tabular data, especially data stored in a _Relational Database_.

> Provides methods to query and update data in a database, and is oriented toward relational databases.

JDBC helps you write Java applications that manage these three programming activities:

1. Connect to a data source, like a database.
2. Send queries and update statements to the database.
3. Retrieve and process the results received from the database in answer to your query.

JDBC allows multiple implementations to exist and be used by the same application. The API provides a mechanism for dynamically loading the correct Java packages and registering them with the JDBC Driver Manager, which is used as a connection factory.

## Components

1. __JDBC API__: Provides programmatic access to relational data. It can also interact with multiple data sources in a distributed, heterogeneous environment. It is part of the Java platform, which includes in the Java SE and Java EE. The JDBC 4.0 API is divided into `java.sql` and `javax.sql`.

2. __JDBC Driver Manager__: `DriverManager` class defines objects which can connect Java applications to a JDBC driver.

3. __JDBC Test Suite__: Helps you determine that JDBC drivers will run your program. These tests are not comprehensive or exhaustive, but they do exercise many of the important features in the JDBC API.

4. __JDBC-ODBC Bridge__: Provides JDBC access via ODBC drivers.
