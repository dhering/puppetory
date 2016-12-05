# Puppetory 1.0
Fortgeschrittene Software-Technologie <br />
Sommersemester 2015 <br />
Team 06 <br />

## 1. Inhalt
Der Prototype Puppetory in Version 1.0 wird als Zip Paket ausgeliefert und
enthält alle Quelltexte, Ressourcen und Tests. Ebenso sind Testdaten
sowie eine voll funktionsfähige Umgebung für Integrationstest enthalten.

## 2. Abhängigkeiten
Puppetory verwendet Vagrant um anhand von Konfigurationsdateien ein virtuelle
Box inklusive Betriebssystem, benötigter Software sowie aller Konfigurationen
einzurichten und zu starten.

Die Applikation kann kostenfrei vom Anbieter heruntergeladen werden:
https://www.vagrantup.com/downloads.html

Die virtuelle Box verwendet zwei Prozessorkerne, 1024 MB Arbeitsspeicher
und benötigt etwa 2,5 GB Platz auf der Festplatte. Das Gastsytem sollte
entsprechende Ressourcen frei haben.

## 3. Anwendung starten
Die Datei mit dem Namen puppetory.zip muss entpackt werden.

Um nun eine vollständige Integrationsumgebung starten zu können muss der
folgende Ordner aufgerufen werden:
puppetory/vagrant/full

Der Ordner enthält das Vagrantfile. An dieser Stelle kann Vagrant mit folgendem
Befehlt für die Kommandozeile gestartet werden:
vagrant up

Folgende Schritte werden nun ausgeführt:
- Basis Image wird heruntergeladen
- Software wird installiert: mongoDB, Java, Tomcat, Maven, etc.
- Daten werden in die mongoDB importiert
- Maven läd Abhängigkeiten herunter und baut die Applikation
- Die Applikation wird als WAR im Tomcat installiert

Vagrant richtet ein NAT/PAT ein, bei dem der Port 8080 des Gastsystems mit
Port 8080 der virtuellen Maschine verknüpft werden. Lokale Aufrufe erreichen
somit immer die VM.

Um die Applikation wieder zu stoppen sind folgende Optionen möglich:
- vagrant halt --> VM stoppen
- vagrant suspend --> VM pausieren
- vagrant destroy --> VM löschen

Wenn die VM nicht gelöscht wurde kann diese immer wieder mit folgendem Befehlt
gestartet werden:
vagrant up

## 4. Report aufrufen
Die Reports lassen sich über den einen Browser aufrufen. Folgende Aufrufe wurden
für die Testfälle verwendet:

Report über Linux Server (evtl. lange Laufzeit)
http://localhost:8080/report/linuxServerOverviewWebReport

Report über Linux Server mit Filter auf Betriebssystemversion
http://localhost:8080/report/linuxServerOverviewWebReport?searchFilter=%7Boperatingsystemrelease%3A%227.8%22%7D

Report über Windows Server (evtl. lange Laufzeit)
http://localhost:8080/report/windowsServerOverviewWebReport
