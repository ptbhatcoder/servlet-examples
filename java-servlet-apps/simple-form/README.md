# Simple Form Servlet


### Created by : Pranav Bhat
#### It takes the name of the end user sent as a "name" query parameter , password, gender, preferred programming languages and also a sample instruction and also reveals a secret which the client or the requester had sent to the serverand returns a Hello to that user
### Source : [Java Servlets - A tutorial](https://www.ntu.edu.sg/home/ehchua/programming/java/JavaServlets.html)
### Uses Java servlets

## Pre Required Software:
* Java 1.6 +
* servlet-api ( will be present in the tomcat folder)
* Tomcat

## How to run this
* Go to the folder $(PROJECT_ROOT)/WEB_INF/
* Run the code : javac -cp .:$(CATALINA_HOME)/lib/servlet-api.jar -d classes/ $(find src/* | grep .java)
* Add the folder simple-form into the $CATALINA-HOME/webapps/ folder
* Go to link [Simple Form](http://localhost:8080/simple-form) and then follow other instructions
* You might even see some logs in the tomcat/required server console
