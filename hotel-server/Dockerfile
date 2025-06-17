# Schritt 1: Basis-Image
FROM openjdk:17-jdk-alpine

# Schritt 2: Arbeitsverzeichnis festlegen
WORKDIR /app

# Schritt 3: Jar-Datei kopieren
COPY target/hotel-server-0.0.1-SNAPSHOT.jar app.jar

# Schritt 4: Anwendung starten
ENTRYPOINT ["java", "-jar", "app.jar"]
