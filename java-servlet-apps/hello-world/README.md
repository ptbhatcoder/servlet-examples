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

## How to run this
* Go to the folder $(PROJECT_ROOT)/WEB_INF/
* Run the code : javac -cp .:$(CATALINA_HOME)/lib/servlet-api.jar -d classes/ $(find src/* | grep .java)
* Add the folder hello-world into the $CATALINA-HOME/webapps/ folder
* Go to link [Hello World](http://localhost:8080/hello-world) and then follow other instructions
* You might even see some logs in the tomcat/required server console
