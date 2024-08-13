FROM azul/zulu-openjdk:11-latest
VOLUME /tmp
COPY build/libs/*.jar uniblox.jar
ENTRYPOINT ["java","-jar","/uniblox.jar"]
