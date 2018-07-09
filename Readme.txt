DCMS
Distributed Systems Third Assignment to run the code, follow the below steps

Open DCMS/src/com/Server/DcmsServer
To host in different server machines replace the localhost with the machine IPs as shown below
Endpoint endpointMtl = Endpoint.publish("http://localhost:3333/MTL", mtlServer);
Endpoint endpointDdo = Endpoint.publish("http://localhost:4444/DDO", ddoServer);
Endpoint endpointLvl = Endpoint.publish("http://localhost:5555/LVL", lvlServer);
For Example
Endpoint endpointMtl = Endpoint.publish("http://132.205.4.62:3333/MTL", mtlServer);
Endpoint endpointDdo = Endpoint.publish("http://132.205.4.63:4444/DDO", ddoServer);
Endpoint endpointLvl = Endpoint.publish("http://132.205.4.64:5555/LVL", lvlServer);

Open DCMS/src/com/Client/ClientMain.java and run it.
Open DCMS/src/com/Server/ServerMain.java and run it.
Servers IP addresses configured to localhost in DCMS/src/com/Conf/Constants.java.
UDP Server ports configured to 9999,1111 and 7777 starts listening(Configurable in DCMS/src/com/Conf/Constants.java).
Client and Server Logs are stored in DCMS/Logs/ (Configurable in DCMS/src/com/Conf/Constants.java).
Enter the inputs following the instructions on the client side.