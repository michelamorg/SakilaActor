# SakilaActor
# Connessione al Db --->branch master:
DbConn: connessione al Db postgreSQL con pattern Singleton 
TestConn: verifica della connessione al Db

# Actor--->branch develop (ramo di lavoro):
 Classe model con i propri attributi /proprietà in un contesto di persistenza.
 
# ActorDao: interfaccia con dichiarazione dei metodi.

# ActorDaoImpl: classe che implementa ActorDao in cui si effettuano le query relative ai metodi.

# SakilaActor: Service Endpont Interface che contiene la dichiarazione di tutti i metodi che si desidera includere nel servizio web.
@WebService : annota il servizo 
@SoapBinding: definisce lo stile dei messaggi SOAP nel WSDL , in questo caso DOCUMENT.
@WebMethod: attesta che il metodo è un'operazione del servizio web.

# SakilaActorImpl: classe che implementa i metodi dell'interfaccia. Consisterà nell’implementazione dell'endpoint (Service Endpoint Implementation).

# ManagerSakilaActor: consiste nel controller che effettua la validazione sui parametri di input . 
Una volta accertata la validazione viene chiamata la classe SakilaActorImpl dove si è effettua la logica.

# SakilaActorPublisher: editor di endpoint che distribuisce effettivamente il servizio Web.
Crea e pubblica l'endpoint per l'oggetto implementatore specificato a un determinato indirizzo. L'infrastruttura server necessaria verrà creata e configurata dall'implementazione JAX-WS. È necessario eseguire l'editor per rendere disponibile il servizio Web ai client.

# SoapUi: una volta pubblicato l'endpoint con l’aggiunta di "?wsdl " si ottiene il WSDL. 
Documento che detiene i dettagli tecnici di implementazione del servizo web.
In SoapUI, inserendo il WSDL , posso testare le API. 
