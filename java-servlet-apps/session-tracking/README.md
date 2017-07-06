# Session Testing Servlet


### Created by : Pranav Bhat
#### It directly opens up as the default page in the web browser since it is a welcome file. It shows the number of times the particular page was accesses from any browser, along with the last access time, creation time and max inactive time interval. User also has an option to invalidate a session as well, and also some refresh buttons are present
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
* Go to link [Session Testing](http://localhost:8080/session-tracking) and then follow other instructions
* You might even see some logs in the tomcat/required server console
