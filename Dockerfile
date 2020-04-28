From openjdk:8
copy ./target/flightticket-0.0.1-SNAPSHOT.jar flightticket-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","flightticket-0.0.1-SNAPSHOT.jar"]
