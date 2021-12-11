Name: Braedon Dillon
Student Number: 101068213

How to run: 

The whole project consists of only two files. These files are 

1. postgresql-42.3.1.jar 

and 

2. App.java

The first file is the Postgres driver for JDBC. To make use of this driver it needs to be added to the class path and then JDBC
should be able to find it automatically as long as you're not using a very old JDK

The second file is my entire implementation. This can be compiled with javac and ran with java but I usually run it in an IDE such as VSCODE which makes handling the postgres driver much easier 
