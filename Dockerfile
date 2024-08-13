FROM azul/zulu-openjdk:11-latest
VOLUME /tmp
COPY build/libs/*.jar uniblox.jar
COPY products.json products.json
COPY users.json users.json
ENTRYPOINT ["java","-jar","/uniblox.jar"]
