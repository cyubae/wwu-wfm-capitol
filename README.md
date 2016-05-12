# wwu-wfm-capitol

# partner-interface bietet Rest-Schnittstellen.

1. Builden der war-Datei
2. Camunda starten, localhost:8080 aufrufen, Administration Console aufrufen
3. Create Deployment w�hlen ->  Add -> partner-interface-0.0.1-SNAPSHOT.war ausw�hlen
4. Name und Runtime Name beide auf partner-interface.war �ndern

Damit ist das Rest-Interface deployed. Es nimmt POST-Anfragen unter folgenden adressen entgegen:

localhost:8080/partner-interface/contracting

localhost:8080/partner-interface/casehandling

Ausprobieren kann man die Funktionsweise einfach per Firefox-Addon "HttpRequester"
Sende an eine der beiden obrigen Adressen einen POST-Request vom content-type "application/json" mit folgendem inhalt:

{
    "id":"1",
    "name":"Max Mustermann",
    "description":"Max will ein Auto",
    "sum":500
}

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