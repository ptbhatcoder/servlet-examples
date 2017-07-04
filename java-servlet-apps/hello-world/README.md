# Hello World Servlet


### Created by : Pranav Bhat
#### It takes the name of the end user sent as a "name" query parameter and returns a Hello to that user
#### It also sends other information about the request like the 
##### Request URI
##### Request URL
##### PathInfo
##### Remote Address
##### A random seed
### Source : [Java Servlets - A tutorial](https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html)
### Uses Java servlets

## Pre Required Software:
* Java 1.6 +
* servlet-api ( will be present in the tomcat folder)
* Tomcat

# How to run this
* Run the code : javac -cp .:/Applications/tomcat/lib/servlet-api.jar -d /Applications/tomcat/webapps/hello-world/WEB-INF/classes/ $(find /Applications/tomcat/webapps/hello-world/WEB-INF/src/* | grep .java)
* Add the folder hello-world into the $CATALINA-HOME/webapps/ folder
* Go to link [Hello World](http://localhost:8080/hello-world) and then follow other instructions
* You might even see some logs in the tomcat/required server console