# Titel:
1. Topologie for hotelico architecture

Microkernel-Architecture: core-system and plugin-components

# Status:
Accepted

# Kontext:
Application have to work on pc and in mobile browser.
Some hotel do not need all the functions, so optional plugin to main module are optional extendable?

##  Alternativen: 
mobile-app, monolith, JSP-Frontend generated on backend. 

# Entscheidung:
Wir werden asynchrones Messaging zu verwenden, um die spezifische zeitbegrenzte Hotel-offers an die verschiedene 
Kunden-Gruppen zu liefern.

Wir werden Web-Sockets die für sozialle Messaging zwischen Hotelgäste verwenden.

Single Site Application based on AngularJs Framework as separate User Interface. Separate deployable 
from server-based system. Communication with backend based on REST and Web-Sockets.

# Konsequenzen:
Für die einzelne (zeitlich offline) Gäste wird die Garantie der Zustellung der Hoteloffers nicht garantierbar und
komplexer nachvorschbar und ist in unserem Fall nicht sehr wichtig.

Viele Frontend-Logik wird mit AngularJs implementiert und im Browser ausführbar. Die Kommunikationskosten mit server sind zu minimieren,
insbesondere wenn schlechte oder instabile Mobile-Empfang wird, soll die Frontend-Applikation weiter laufen.
Die chat-Nachrichten sollen automatisch nach der Herstellung der Empfang zugestellt werden. Shared-worker soll im Hintegrund der Browser 
weiter laufen und bereit sein, die Nachrichten zu erhalten.

# Compilance:

# Notizen:

Author: Eugen F.
Geändert von: Eugen F.
Letzte Änderung: 01.07.2022