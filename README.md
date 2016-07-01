# wwu-wfm-capitol

# Build Reihenfolge 

1. Partner Interface
2. MessagingService
4. capitol-persistence
3. Processes (contracting and settlement)

# Deploy Reihenfolge 

Selbe wie oben

# partner-interface bietet REST interface.

1. Builden der war-Datei
2. Camunda starten, localhost:8080 aufrufen, Administration Console aufrufen
3. Create Deployment wählen ->  Add -> partner-interface-0.0.1-SNAPSHOT.war auswählen
4. Name und Runtime Name beide auf partner-interface.war ändern

Für genaue Daten, check die Readme im Porjekt des partner interfaces

# MessageService ermöglicht Prozessen das senden von Nachrichten

1. Anwendung bauen
2. Wie oben beschrieben deployen, Name ist "MessagingService.jar"
3. In jedem Task der den MessagingService verwenden soll kann er mittels folgenden Codezeilen injected werden:

 @EJB(lookup="java:global/MessagingService/MessageServiceImpl!de.unimuenster.wfm.capitol.service.MessageService")
	  private MessageService messageService;

Damit eclipse den MessageService erkennt, muss das Projekt des entsprechenden Prozesses konfiguriert werden:

1. Rechtsklick auf das Projekt -> Properties
2. Java Build Path aufrufen
3. Den Reiter "Projects" ausrufen -> Add -> MessagingService auswählen

Damit die Klassen des Services für die Prozesse sichtbar sind, muss der Ordner "de" in camunda/server/wildfly-8.2.1.Final/modules eingefügt werden