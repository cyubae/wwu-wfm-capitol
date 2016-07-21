# wwu-wfm-capitol

#Anpassungen am Code

Um mit BVIS kommunizieren zu k�nnen muss die Variable DESTINATION_URL in den Klassen "SendContractProposalBL" und "SendRejectionBL" (Contracting) bzw. in der Klasse "SendDecisionBL" (Settlement) auf den deployment-Ort von BVIS angepasst werden.

# Build Reihenfolge 

(wird bei Ausf�hrung von mvn clean install im Hauptordner automatisch eingehalten)

1. Partner Interface
2. MessagingService
4. capitol-persistence 
3. Processes (contracting and settlement)*

# Deployment

Das Projekt ist auf die Wildfly-Distribution von Camunda ausgelegt. Nach dem Herunterladen und entpacken muss zuerst ein Wildfly-User mit dem Script add-user.bat angelegt werden. Danach kann der Server gestartet und �ber localhost:8080 auf die WebConsole zugegriffen werden. Von hier aus kann das Projekt deployt werden:

1. Deploye HSQLDB.jar (aus dem Ordner "database_settings")
2. Erstellen einer neuen Non-XA Datasource (Die Anleitung hierf�r befindet sich ebenfalls in "database_settings")
3. Neustarten des Servers, um die Datasource zu aktivieren.
4. Deployment von Partner-Interface und MessagingService
5. Deployment von Contracting und Settlement

#Herunterfahren

Aufgrund eines Problems mit Wildfly/JBoss (https://issues.jboss.org/browse/JBWS-3842?_sscc=t) muss vor dem Abschalten des Servers ein Prozess in der Wildfly Console disabled werden, da ansonsten der Server nicht wieder bootet.

# partner-interface bietet REST interface.

1. Builden der war-Datei
2. Camunda starten, localhost:8080 aufrufen, Administration Console aufrufen
3. Create Deployment w�hlen ->  Add -> partner-interface-0.0.1-SNAPSHOT.war ausw�hlen
4. Name und Runtime Name beide auf partner-interface.war �ndern

F�r genaue Daten, check die Readme im Porjekt des partner interfaces

# MessageService erm�glicht Prozessen das senden von Nachrichten

1. Anwendung bauen
2. Wie oben beschrieben deployen, Name ist "MessagingService.jar"
3. In jedem Task der den MessagingService verwenden soll kann er mittels folgenden Codezeilen injected werden:

 @EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	  private MessageService messageService;

Damit eclipse den MessageService erkennt, muss das Projekt des entsprechenden Prozesses konfiguriert werden:

1. Rechtsklick auf das Projekt -> Properties
2. Java Build Path aufrufen
3. Den Reiter "Projects" ausrufen -> Add -> MessagingService ausw�hlen

Damit die Klassen des Services f�r die Prozesse sichtbar sind, muss der Ordner "de" in camunda/server/wildfly-8.2.1.Final/modules eingef�gt werden