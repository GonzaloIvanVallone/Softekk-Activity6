#se indica la version del jdk
FROM openjdk:17
#indica que esta creando un volumen en el contenedor que servira para persistir datos
VOLUME /tmp
#se indica el puerto
EXPOSE 8080
# Se copian los archivos del directorio "scr" al contenedor
COPY src /home/app/src
# Se copia el archivo pom.xml al contenedor
COPY pom.xml /home/app
#se indica el .jar en una variable
ARG JAR_FILE=target/Softekk-0.0.1-SNAPSHOT.jar
#y se le da un nombre mas abreviado
ADD ${JAR_FILE} app.jar
#indicamos que se va ejecutar una aplicacion java empaquetada en un archivo jar
ENTRYPOINT ["java","-jar","/app.jar"]