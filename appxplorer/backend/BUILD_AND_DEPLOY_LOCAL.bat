RMDIR /q /s C:\OSS\apache-tomcat-8.0.33\webapps\ax-adminservices\
RMDIR /q /s C:\OSS\apache-tomcat-8.0.33\webapps\ax-apservices\
RMDIR /q /s C:\OSS\apache-tomcat-8.0.33\webapps\ax-ciservices\

mvn clean install

COPY ax-marketservices\ax-adminservices\target\ax-adminservices.war C:\OSS\apache-tomcat-8.0.33\webapps\ax-adminservices.war
COPY ax-marketservices\ax-apservices\target\ax-apservices.war C:\OSS\apache-tomcat-8.0.33\webapps\ax-apservices.war
COPY ax-marketservices\ax-ciservices\target\ax-ciservices.war C:\OSS\apache-tomcat-8.0.33\webapps\ax-ciservices.war
